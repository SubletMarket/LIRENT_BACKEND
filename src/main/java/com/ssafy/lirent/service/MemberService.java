package com.ssafy.lirent.service;

import com.ssafy.lirent.model.dto.MemberDto;
import com.ssafy.lirent.model.dto.member.MemberUpdateRequestDto;
import com.ssafy.lirent.model.mapper.MemberMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//TODO: accessToken 접속시 갱신기능

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
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public Integer login(String email, String password) {
//		Integer memberId = mapper.login(email, password);
//		System.out.println("haha" + email + " " + password);
//		if (memberId == null) { // 해당하는 멤버가 없을 경우
//			return null;
//		} else {
//			return memberId;
//		}
		 // 입력된 비밀번호를 해싱
	    String hashedPassword = hashPassword(password);

	    // 해싱된 비밀번호로 로그인 검증
	    Integer memberId = mapper.login(email, hashedPassword);
	    System.out.println("로그인 요청: " + email + " / " + hashedPassword);

	    return memberId; // 로그인 성공 시 memberId 반환, 실패 시 null 반환
	}

	public boolean regist(MemberDto newMember) {
//		int result = mapper.regist(newMember);
//
//		if (result >= 1) {
//			return true;
//		} else {
//			return false;
//		}
		String hashedPassword = hashPassword(newMember.getPassword());
		newMember.setPassword(hashedPassword); // 해싱된 비밀번호로 설정

		int result = mapper.regist(newMember); // 매퍼 호출

		return result >= 1; // 성공 여부 반환
	}

	public MemberDto getInfo(int memberId) {
		return mapper.getInfo(memberId);
	}

	public boolean update(MemberDto member) {
		int result = mapper.update(member);

		if (result >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delete(int memberId) {
		int result = mapper.delete(memberId);

		if (result >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 algorithm not found", e);
		}
	}
	public boolean isEmailExists(String email) {
	    return mapper.isEmailExists(email); // Mapper나 Repository 계층에서 확인
	}


}