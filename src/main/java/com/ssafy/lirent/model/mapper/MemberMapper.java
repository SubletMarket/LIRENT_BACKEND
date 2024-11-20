package com.ssafy.lirent.model.mapper;

import com.ssafy.lirent.model.dto.MemberDto;
import com.ssafy.lirent.model.dto.member.MemberRegistRequestDto;
import com.ssafy.lirent.model.dto.member.MemberUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public int sel();
    public MemberDto getMemberById(int memberId);

    /**
     * 해당 이메일, 패스워드에 해당하는 멤버의 ID 반환
     * @param email
     * @param password
     * @return MemberId
     */
    public Integer login(String email, String password);
    public MemberDto getInfo(int memberId);
    public int regist(MemberDto newMember);
    /**
     * 멤버 ID를 기준으로 내용을 갱신
     * @param memberDto
     * @return 영향받은 컬럼 개수
     */
    public int update(MemberDto memberDto);

    public int delete(int memberId);
}
