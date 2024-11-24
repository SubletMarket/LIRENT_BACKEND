package com.ssafy.lirent.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SubleaseDto {
    private int subleaseId;
    private int memberId;
    private Date startDate;
    private Date endDate;
    private int deposit;
    private int price;
    private Timestamp created;
}
