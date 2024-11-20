package com.ssafy.lirent.model.dto;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChatsDto {
    private int subleaseId;
    private int memberId;
    private boolean isOwner;
    private String message;
    private LocalDateTime created;
}