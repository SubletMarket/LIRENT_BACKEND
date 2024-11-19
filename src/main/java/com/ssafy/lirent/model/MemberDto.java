package com.ssafy.lirent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    int memberId;
    String email;
    String password;
    String phone;
    String address;
    String nickname;
    Timestamp created;
}
