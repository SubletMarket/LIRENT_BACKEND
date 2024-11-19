package com.ssafy.lirent.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    Timestamp created;
}