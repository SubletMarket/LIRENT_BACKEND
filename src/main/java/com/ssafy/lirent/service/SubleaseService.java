package com.ssafy.lirent.service;

import com.ssafy.lirent.model.dto.SubleaseDealDto;
import com.ssafy.lirent.model.dto.SubleaseDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseGetResponseDto;
import com.ssafy.lirent.model.mapper.SubleaseDealMapper;
import com.ssafy.lirent.model.mapper.SubleaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SubleaseService {
    SubleaseMapper subleaseMapper;
    @Autowired
    public SubleaseService(SubleaseMapper mapper) {
        this.subleaseMapper = mapper;
    }

    public boolean insert(SubleaseDto dto) {
        int result = subleaseMapper.insert(dto);

        if (result >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<SubleaseGetResponseDto> getAllSubleases() {
        return subleaseMapper.selectAll();
    }



    public SubleaseGetResponseDto getSublease(int subleaseId) { return subleaseMapper.select(subleaseId);}
}
