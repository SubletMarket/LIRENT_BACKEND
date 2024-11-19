//package com.ssafy.lirent.controller;
//
//import com.ssafy.lirent.model.MemberDto;
//import com.ssafy.lirent.model.mapper.MemberMapper;
//import com.ssafy.lirent.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/member")
//public class MemberController {
//
//	@Autowired
//	MemberMapper memberMapper;
//
//	@Autowired
//	JwtUtil jwtUtil;
//
//	// 로그인
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
//		String email = request.get("email");
//		String password = request.get("password");
//
//		MemberDto member = memberMapper.findByEmail(email);
//
//		if (member != null && member.getPassword().equals(password)) {
//			String accessToken = jwtUtil.createAccessToken(String.valueOf(member.getMemberId()));
//			String refreshToken = jwtUtil.createRefreshToken(String.valueOf(member.getMemberId()));
//			Map<String, String> response = new HashMap<>();
//			response.put("accessToken", accessToken);
//			response.put("refreshToken", refreshToken);
//			return ResponseEntity.ok(response);
//		} else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//		}
//	}
//
//	// 로그아웃 (JWT는 클라이언트에서 처리)
//	@PostMapping("/logout")
//	public ResponseEntity<String> logout() {
//		// 클라이언트가 JWT를 삭제하도록 요청
//		return ResponseEntity.ok("Logout successful");
//	}
//
//	// 회원가입
//	@PostMapping
//	public ResponseEntity<String> register(@RequestBody MemberDTO memberDTO) {
//		try {
//			memberMapper.insert(memberDTO);
//			return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
//		}
//	}
//
//	// 회원 정보 조회
//	@GetMapping
//	public ResponseEntity<?> getMemberInfo(@RequestHeader("Authorization") String token) {
//		String username = jwtUtil.extractUsername(token.substring(7));
//		MemberDTO member = memberMapper.findByUsername(username);
//
//		if (member != null) {
//			return ResponseEntity.ok(member);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
//		}
//	}
//
//	// 로그인 상태 확인
//	@GetMapping("/status")
//	public ResponseEntity<Map<String, Boolean>> checkLoginStatus(@RequestHeader("Authorization") String token) {
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("loggedIn", jwtUtil.validateToken(token.substring(7)));
//		return ResponseEntity.ok(response);
//	}
//
//	// 회원 수정
//	@PutMapping
//	public ResponseEntity<String> updateMember(@RequestBody MemberDTO memberDTO,
//			@RequestHeader("Authorization") String token) {
//		String username = jwtUtil.extractUsername(token.substring(7));
//		MemberDTO member = memberMapper.findByUsername(username);
//
//		if (member != null) {
//			member.setName(memberDTO.getName());
//			member.setEmail(memberDTO.getEmail());
//			memberMapper.update(member);
//			return ResponseEntity.ok("Member updated successfully");
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found");
//		}
//	}
//
//	// 비밀번호 재설정
//	@PutMapping("/password-reset")
//	public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
//		String username = request.get("username");
//		String email = request.get("email");
//		String newPassword = request.get("newPassword");
//
//		MemberDTO member = memberMapper.findByUsername(username);
//
//		if (member != null && member.getEmail().equals(email)) {
//			member.setPassword(newPassword);
//			memberMapper.update(member);
//			return ResponseEntity.ok("Password reset successfully");
//		} else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials");
//		}
//	}
//}
