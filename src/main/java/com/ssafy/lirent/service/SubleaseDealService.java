package com.ssafy.lirent.service;

import com.ssafy.lirent.model.dto.SubleaseDealDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseDealAddRequestDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseDealGetResponseDto;
import com.ssafy.lirent.model.mapper.SubleaseDealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubleaseDealService {
    SubleaseDealMapper mapper;

    @Autowired
    public SubleaseDealService(SubleaseDealMapper mapper) {
        this.mapper = mapper;
    }

    public List<SubleaseDealGetResponseDto> getDealsBySubleaseId(int subleaseId) {
        return mapper.selectBySubleaseId(subleaseId);
    }

    public boolean insert(SubleaseDealAddRequestDto newDeal) {
        return mapper.insert(newDeal) >= 1 ? true : false;
    }
}
