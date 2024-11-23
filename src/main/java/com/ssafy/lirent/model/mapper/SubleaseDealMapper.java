package com.ssafy.lirent.model.mapper;

import com.ssafy.lirent.model.dto.SubleaseDealDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseDealAddRequestDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseDealGetResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubleaseDealMapper {
    List<SubleaseDealGetResponseDto> selectByMemberId(int memberId);
    List<SubleaseDealGetResponseDto> selectBySubleaseId(int subleaseId);
    int insert(SubleaseDealAddRequestDto newDeal);
    int accept(int dealId);
}
