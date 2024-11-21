package com.ssafy.lirent.service;

import com.ssafy.lirent.model.dto.SubleaseDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseGetResponseDto;
import com.ssafy.lirent.model.mapper.SubleaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubleaseService {
    SubleaseMapper mapper;

    @Autowired
    public SubleaseService(SubleaseMapper mapper) {
        this.mapper = mapper;
    }

    public boolean insert(SubleaseDto dto) {
        int result = mapper.insert(dto);

        if (result >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<SubleaseGetResponseDto> getAllSubleases() {
        return mapper.selectAll();
    }
}
