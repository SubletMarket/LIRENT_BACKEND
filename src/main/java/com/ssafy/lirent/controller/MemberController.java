package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.MemberDto;
import com.ssafy.lirent.model.dto.member.MemberLoginRequestDto;
import com.ssafy.lirent.model.dto.member.MemberLoginResponseDto;
import com.ssafy.lirent.model.dto.member.MemberRegistRequestDto;
import com.ssafy.lirent.model.dto.member.MemberUpdateReqeustDto;
import com.ssafy.lirent.service.MemberService;
import com.ssafy.lirent.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/token")
    @Operation(summary = "JWT기반 로그인")
    ResponseEntity<MemberLoginResponseDto> login(MemberLoginRequestDto dto) {
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

    @PostMapping
    @Operation(summary = "회원가입")
    ResponseEntity<Void> regist(MemberRegistRequestDto dto) {
        MemberDto newMember = new MemberDto();
        newMember.setEmail(dto.getEmail());
        newMember.setPassword(dto.getPassword());
        newMember.setNickname(dto.getNickname());
        newMember.setPhone(dto.getPhone());
        newMember.setAddress(dto.getAddress());

        if (service.regist(newMember)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    @Operation(summary = "정보 수정")
    ResponseEntity<Void> update(MemberUpdateReqeustDto dto, HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        MemberDto member = new MemberDto();
        member.setMemberId(memberId);
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword());
        member.setPhone(dto.getPhone());
        member.setNickname(dto.getNickname());
        member.setAddress(dto.getAddress());

        if (service.update(member)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "회원 삭제")
    ResponseEntity<Void> delete(HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        if (service.delete(memberId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
