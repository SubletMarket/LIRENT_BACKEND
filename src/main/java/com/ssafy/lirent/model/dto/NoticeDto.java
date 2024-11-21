package com.ssafy.lirent.model.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NoticeDto {
	private int boardId;
	private int memberId;
	private String title;
	private String context;
	private String nickname;
	private String category;
	private LocalDateTime createdDate;
}
