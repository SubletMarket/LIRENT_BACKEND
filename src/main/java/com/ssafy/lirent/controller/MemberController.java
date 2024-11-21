package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.MemberDto;
import com.ssafy.lirent.model.dto.RoomInfoDto;
import com.ssafy.lirent.model.dto.member.MemberLoginRequestDto;
import com.ssafy.lirent.model.dto.member.MemberLoginResponseDto;
import com.ssafy.lirent.model.dto.member.MemberRegistRequestDto;
import com.ssafy.lirent.model.dto.member.MemberUpdateReqeustDto;
import com.ssafy.lirent.service.MemberService;
import com.ssafy.lirent.service.RoomInfoService;
import com.ssafy.lirent.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    MemberService memberService;
    RoomInfoService roomInfoService;
    JwtUtil jwtUtil;

    public MemberController(MemberService memberService, RoomInfoService roomInfoService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.roomInfoService = roomInfoService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/token")
    @Operation(summary = "JWT기반 로그인")
    ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto dto) {
        Integer memberId = memberService.login(dto.getEmail(), dto.getPassword());
        
        System.out.println(dto.getEmail());
        System.out.println(dto.getPassword());

        System.out.println("Here!!");
        System.out.println(memberId); 
        
        if (memberId == null) { // 멤버가 없을 경우
            return ResponseEntity.notFound().build();
        }

        // 멤버가 있을 경우
        String accessToken = jwtUtil.createAccessToken(memberId);
        MemberLoginResponseDto responseDto = new MemberLoginResponseDto();
        responseDto.setAccessToken(accessToken);
        
        System.out.println(ResponseEntity.ok(responseDto));
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    @Operation(summary = "회원가입")
    ResponseEntity<Void> regist(@RequestBody MemberRegistRequestDto dto) {
        // 해당 멤버와 관련된 집 정보 생성
        String sigunguCode = dto.getBcode().substring(0, 5);
        String dongCode = dto.getBcode().substring(5, 10);

        RoomInfoDto newRoomInfo = roomInfoService.initRoomInfo(sigunguCode, dongCode, dto.getBun(), dto.getJi());
        newRoomInfo.setAddress(dto.getAddress());
        newRoomInfo.setBcode(dto.getBcode());
        newRoomInfo.setDong(dto.getDong());
        newRoomInfo.setHo(dto.getHo());
        newRoomInfo.setFloor(dto.getFloor());
        newRoomInfo.setArea(dto.getArea());
        newRoomInfo.setRooms(dto.getRooms());
        newRoomInfo.setBathrooms(dto.getBathrooms());

        if (roomInfoService.insert(newRoomInfo)) {
            // 멤버 생성
            MemberDto newMember = new MemberDto();
            newMember.setEmail(dto.getEmail());
            newMember.setPassword(dto.getPassword());
            newMember.setNickname(dto.getNickname());
            newMember.setPhone(dto.getPhone());
            newMember.setAddress(dto.getAddress());
            newMember.setRoomId(newRoomInfo.getRoomId());

            if (memberService.regist(newMember)) {
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
