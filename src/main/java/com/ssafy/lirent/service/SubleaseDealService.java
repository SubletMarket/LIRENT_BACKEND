package com.ssafy.lirent.service;

import com.ssafy.lirent.model.dto.SubleaseDealDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseDealAddRequestDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseDealGetResponseDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseGetResponseDto;
import com.ssafy.lirent.model.mapper.SubleaseDealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubleaseDealService {
    SubleaseDealMapper mapper;

    SubleaseService subleaseService;

    @Autowired
    public SubleaseDealService(SubleaseDealMapper mapper, SubleaseService subleaseService) {
        this.mapper = mapper;
        this.subleaseService = subleaseService;
    }

    public List<SubleaseDealGetResponseDto> getDealsByMemberId(int memberId) {
        return mapper.selectByMemberId(memberId);
    }
    public List<SubleaseDealGetResponseDto> getDealsBySubleaseId(int subleaseId) {
        return mapper.selectBySubleaseId(subleaseId);
    }

    public boolean insert(SubleaseDealAddRequestDto newDeal) {
        return mapper.insert(newDeal) >= 1;
    }

    public boolean accept(int dealId, int ownerId) {
        // ownerId의 sublease 가져오기
        SubleaseGetResponseDto subleaseDto = subleaseService.getLatestSubleaseByMemberId(ownerId);

        // 가져온 sublease를 이용해 계약 리스트 확인
        List<SubleaseDealGetResponseDto> deals = mapper.selectBySubleaseId(subleaseDto.getSubleaseId());

        // dealId가 계약 리스트 중 존재할경우, accpet = true로 상태 업데이트
        for (SubleaseDealGetResponseDto deal : deals) {
            if (deal.getDealId() == dealId) {
                mapper.accept(dealId);
                return true;
            }
        }

        return false;
    }

    public boolean delete(int dealId, int ownerId) {
        return mapper.delete(dealId, ownerId) >= 1;
    }
}
