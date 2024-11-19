package com.ssafy.lirent.model.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegistRequestDto {
    String email;
    String password;
    String phone;
    String address;
    String nickname;
}
