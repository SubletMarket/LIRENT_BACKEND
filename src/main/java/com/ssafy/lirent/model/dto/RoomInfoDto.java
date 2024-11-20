package com.ssafy.lirent.model.dto;

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
public class RoomInfoDto {
    int roomId;
    String address;
    String bcode;
    String dong;
    String ho;
    int buildingFloor;
    String buildingType;
    Date buildingApproveDate;
    int buildingElevatorNum;
    int floor;
    double area;
    int rooms;
    int bathrooms;
    Timestamp created;
}
