package com.ssafy.lirent.model.dto.notice;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NoticeRequestDto {
	private int boardId;
    private int memberId;
    private String title;
    private String context;    
	private LocalDateTime createdDate;
}

