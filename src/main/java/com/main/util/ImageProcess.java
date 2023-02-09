package com.main.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ImageProcess {
	
	/**
	 * 이미지 크기를 줄이는 메서드
	 * @param multipartFile 원본 파일
	 * @return resized image
	 */
	@Transactional
	public MultipartFile resizeImage (MultipartFile multipartFile, int targetWidth) {
		try {
			// 이미지 처리를 위해 버퍼드이미지로 변환해야 함
			BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());
			int originalHeight = originalImage.getHeight();
			int originalWidth = originalImage.getWidth();
			String fileFormat = multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1);
			
			// 이미지 크기가 충분히 작으면 처리할 필요가 없음
			if(originalWidth <= 450) return multipartFile;
			
			// 목표로 하는 이미지의 가로 사이즈는 450px
			int targetHeight = targetWidth * originalHeight / originalWidth;
			
			// 이미지 처리, SMOOTH 스케일을 주면 속도보다 이미지의 부드러움, FAST 스케일을 주면 부드러움 보단 속도
			Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
			BufferedImage newImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = newImage.getGraphics();
			graphics.drawImage(resizedImage, 0, 0, null);
			graphics.dispose();
			
			// 리사이즈된 이미지를 MultipartFile로 바꾸기 위한 전처리
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(newImage, fileFormat, baos);
			baos.flush();
			
			return new ConvertToMultipartFile(multipartFile.getOriginalFilename(), fileFormat, multipartFile.getContentType(), baos.toByteArray());
		} catch (IOException e) {
			System.err.println("파일 리사이즈 실패!");
			e.printStackTrace();
		}
		return null;
	}
	
}
