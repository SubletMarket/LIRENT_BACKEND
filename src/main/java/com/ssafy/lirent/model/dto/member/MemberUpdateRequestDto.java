package com.ssafy.lirent.model.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberUpdateRequestDto {
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
}
