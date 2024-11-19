package com.ssafy.lirent.service;

import com.ssafy.lirent.model.MemberDto;
import com.ssafy.lirent.model.mapper.MemberMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class MemberService {
    MemberMapper mapper;

    @Autowired
    public MemberService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 이메일과 비밀번호를 받아 AccessToken, refreshToken을 반환합니다.
     * @param email
     * @param password
     * @return
     */
    public Integer login(String email, String password) {
        Integer memberId = mapper.login(email, password);

        if (memberId == null) { // 해당하는 멤버가 없을 경우
            return null;
        } else {
            return memberId;
        }
    }
}
