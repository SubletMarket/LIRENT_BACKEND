package com.ssafy.lirent.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class SubleaseDealDto {
    int dealId;
    int subleaseId;
    int contractorId;
    Date startDate;
    Date endDate;
    int deposit;
    int totalPrice;
    Timestamp created;
}
