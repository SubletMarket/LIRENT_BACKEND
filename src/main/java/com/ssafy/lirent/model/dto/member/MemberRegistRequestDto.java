package com.ssafy.lirent.model.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegistRequestDto {
    // member 생성용
    String email;
    String password;
    String phone;
    String address;
    String nickname;

    // member의 home 정보
    boolean park;
    int buildingElevatorNum;
    int floor;
    int area;
    int rooms;
    int bathrooms;
}
