package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.member.MemberLoginRequestDto;
import com.ssafy.lirent.model.dto.member.MemberLoginResponseDto;
import com.ssafy.lirent.service.MemberService;
import com.ssafy.lirent.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    MemberService service;
    JwtUtil jwtUtil;

    @Autowired
    public MemberController(MemberService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "JWT기반 로그인")
    @PostMapping ResponseEntity<MemberLoginResponseDto> login(MemberLoginRequestDto dto) {
        Integer memberId = service.login(dto.getEmail(), dto.getPassword());

        if (memberId == null) { // 멤버가 없을 경우
            return ResponseEntity.notFound().build();
        }

        // 멤버가 있을 경우
        String accessToken = jwtUtil.createAccessToken(memberId);

        MemberLoginResponseDto responseDto = new MemberLoginResponseDto();
        responseDto.setAccessToken(accessToken);

        return ResponseEntity.ok(responseDto);
    }
}
