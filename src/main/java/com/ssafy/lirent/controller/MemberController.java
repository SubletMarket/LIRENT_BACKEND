package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.MemberDto;
import com.ssafy.lirent.model.dto.member.MemberLoginRequestDto;
import com.ssafy.lirent.model.dto.member.MemberLoginResponseDto;
import com.ssafy.lirent.model.dto.member.MemberRegistRequestDto;
import com.ssafy.lirent.model.dto.member.MemberUpdateRequestDto;
import com.ssafy.lirent.service.MemberService;
import com.ssafy.lirent.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    MemberService memberService;
    JwtUtil jwtUtil;

    @Autowired
    public MemberController(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/token")
    @Operation(summary = "JWT기반 로그인")
    ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto dto) {
        Integer memberId = memberService.login(dto.getEmail(), dto.getPassword());

        if (memberId == null) { // 멤버가 없을 경우
            return ResponseEntity.notFound().build();
        }

        // 멤버가 있을 경우
        String accessToken = jwtUtil.createAccessToken(memberId);

        MemberLoginResponseDto responseDto = new MemberLoginResponseDto();
        responseDto.setAccessToken(accessToken);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @Operation(summary = "회원 정보 가져오기")
    ResponseEntity<MemberDto> getInfo(HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        MemberDto member = memberService.getInfo(memberId);

        if (member == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(member);
        }
    }

    @PostMapping
    @Operation(summary = "회원가입")
    ResponseEntity<Void> regist(@RequestBody MemberRegistRequestDto dto) {
        MemberDto newMember = new MemberDto();
        newMember.setEmail(dto.getEmail());
        newMember.setPassword(dto.getPassword());
        newMember.setNickname(dto.getNickname());
        newMember.setPhone(dto.getPhone());
        newMember.setAddress(dto.getAddress());
        newMember.setPark(dto.isPark());
        newMember.setBuildingElevatorNum(dto.getBuildingElevatorNum());
        newMember.setFloor(dto.getFloor());
        newMember.setArea(dto.getArea());
        newMember.setRooms(dto.getRooms());
        newMember.setBathrooms(dto.getBathrooms());

        if (memberService.regist(newMember)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    @Operation(summary = "정보 수정")
    ResponseEntity<Void> update(@RequestBody MemberUpdateRequestDto dto, HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        MemberDto member = new MemberDto();
        member.setMemberId(memberId);
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword());
        member.setNickname(dto.getNickname());
        member.setPhone(dto.getPhone());
        member.setAddress(dto.getAddress());
        member.setPark(dto.isPark());
        member.setBuildingElevatorNum(dto.getBuildingElevatorNum());
        member.setFloor(dto.getFloor());
        member.setArea(dto.getArea());
        member.setRooms(dto.getRooms());
        member.setBathrooms(dto.getBathrooms());

        if (memberService.update(member)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "회원 삭제")
    ResponseEntity<Void> delete(HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        if (memberService.delete(memberId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
