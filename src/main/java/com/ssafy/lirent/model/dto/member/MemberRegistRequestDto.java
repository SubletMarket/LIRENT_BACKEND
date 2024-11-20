package com.ssafy.lirent.model.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // roomInfo 생성용
    String bcode;
    String bun;
    String ji;

    String dong;
    String ho;
    int floor;
    double area;
    int rooms;
    int bathrooms;
}
