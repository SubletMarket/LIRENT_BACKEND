package com.ssafy.lirent.model.dto.sublease;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubleaseAddRequestDto {
    @Schema(description = "날짜 (yyyy-MM-dd 형식)", example = "2024-11-20")
    String startDate;
    @Schema(description = "날짜 (yyyy-MM-dd 형식)", example = "2024-11-20")
    String endDate;
    int deposit;
    int price;
}
