package com.ssafy.lirent.model.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NoticeDto {
	private int id;
	private String memberId;
	private String title;
	private String content;
	private LocalDateTime createdDate;
}
