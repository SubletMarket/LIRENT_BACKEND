package com.ssafy.lirent.model.dto.sublease;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class SubleaseGetResponseDto {
    int subleaseId;
    int memberId;
    Date startDate;
    Date endDate;
    int deposit;
    int price;
    Timestamp created;
    String latitude;
    String longitude;
}
