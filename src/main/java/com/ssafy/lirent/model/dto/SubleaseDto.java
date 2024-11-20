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
    int subleaseId;
    int memberId;
    String buildingName;
    String buildingType;
    Date buildingCompletionDate;
    int usableArea;
    int rooms;
    int bathrooms;
    Date startDate;
    Date endDate;
    int deposit;
    int price;
    Timestamp created;
}
