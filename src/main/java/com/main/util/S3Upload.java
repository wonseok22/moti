package com.main.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Upload {
	private final AmazonS3Client amazonS3Client;
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	// 이미지 리사이즈
	@Transactional
	public MultipartFile resizeImage(MultipartFile multipartFile) {
		try {
			// 이미지 처리를 위해 버퍼드이미지로 변환해야 함
			BufferedImage image = ImageIO.read(multipartFile.getInputStream());
			int originalHeight = image.getHeight();
			int originalWidth = image.getWidth();
			String fileFormat = multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1);
			
			if(originalWidth <= 450) return multipartFile;
			
			MarvinImage marvinImage = new MarvinImage(image);
			
			// 비율 조정
			Scale scale = new Scale();
			scale.load();
			scale.setAttributes();
			scale.setAttribute("newWidth", 450);
			scale.setAttribute("newHeight", 450 * originalHeight / originalWidth);
			scale.process(marvinImage.clone(), marvinImage, null, null, false);
			
			BufferedImage imageNoAlpha = marvinImage.getBufferedImageNoAlpha();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(imageNoAlpha, fileFormat, baos);
			baos.flush();
			
			return new ConvertToMultipartFile(multipartFile.getOriginalFilename(), fileFormat, multipartFile.getContentType(), baos.toByteArray());
		} catch (IOException e) {
			System.err.println("파일 리사이즈 실패!");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String uploadFiles(MultipartFile multipartFile, String dirName) throws IOException {
		File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
				.orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
		return upload(uploadFile, dirName);
	}
	
	public String upload(File uploadFile, String filePath) {
		String fileName = filePath + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
		System.out.println("file name = " + fileName);
		String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
		removeNewFile(uploadFile);
		return uploadImageUrl;
	}
	
	// S3로 업로드
	private String putS3(File uploadFile, String fileName) {
		amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
		return amazonS3Client.getUrl(bucket, fileName).toString();
	}
	
	// 로컬에 저장된 이미지 지우기
	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			System.out.println("File delete success");
			return;
		}
		System.out.println("File delete fail");
	}
	
	// 로컬에 파일 업로드 하기
	private Optional<File> convert(MultipartFile file) throws IOException {
		File convertFile = new File(file.getOriginalFilename());
		if (convertFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			}
			return Optional.of(convertFile);
		}
		return Optional.empty();
	}
	
	//파일 삭제
	public void fileDelete(String fileUrl) throws Exception {
		try {
			try {
				amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileUrl));
			} catch (AmazonServiceException e) {
				System.err.println(e.getErrorMessage());
				System.exit(1);
			}
			
			System.out.println(String.format("[%s] deletion complete", fileUrl));
			
		} catch (Exception e) {
			throw e;
		}
	}
}
