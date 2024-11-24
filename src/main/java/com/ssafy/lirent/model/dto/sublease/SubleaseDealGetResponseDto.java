package com.ssafy.lirent.model.dto.sublease;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubleaseDealGetResponseDto {
    int dealId;
    int subleaseId;
    int contractorId;
    String contractorName;
    String contractorPhone;
    Date startDate;
    Date endDate;
    int deposit;
    int totalPrice;
    boolean accepted;
}
