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
public class MemberDto {
    int memberId;
    String email;
    String password;
    String phone;
    String address;
    String nickname;
    boolean park;
    int buildingElevatorNum;
    int floor;
    int area;
    int rooms;
    int bathrooms;
    String latitude;
    String longitude;
    Timestamp created;
}