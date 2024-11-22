package com.ssafy.lirent.model.dto.sublease;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class SubleaseDealGetResponseDto {
    int dealId;
    int subleaseId;
    int contractorId;
    Date startDate;
    Date endDate;
    int deposit;
    int totalPrice;
}
