package com.main.feed.model.dto;

import com.main.feed.model.entity.Comment;
import com.main.profile.model.entity.Profile;
import com.main.profile.model.entity.ProfileImage;
import com.main.user.model.dto.UserDto;
import com.main.user.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentDto {

	private Long commentId;
	private String userId;
	private String nickname;
	private String profileImageUrl;
	private String content;
	private LocalDateTime createdDate;
	// Comment에서는 매칭된 Feed가 무엇인지 알 필요가 없다.
	
	public static CommentDto toDto (Comment comment) {
		User user = comment.getUser();
		Profile profile = user.getProfile();
		ProfileImage profileImage = profile.getProfileImage();
		return new CommentDto(
				comment.getCommentId(),
				user.getUserId(),
				user.getNickname(),
				profileImage.getProfileImageUrl(),
				comment.getContent(),
				comment.getCreatedDate()
		);
	
	}

}
