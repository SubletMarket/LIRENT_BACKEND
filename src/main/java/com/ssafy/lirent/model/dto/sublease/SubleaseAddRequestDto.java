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
    Date startDate;
    Date endDate;
    int deposit;
    int price;
}
