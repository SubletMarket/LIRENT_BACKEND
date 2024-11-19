package com.ssafy.lirent.model.mapper;

import com.ssafy.lirent.model.MemberDto;
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
}
