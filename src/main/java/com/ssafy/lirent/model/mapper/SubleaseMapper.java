package com.ssafy.lirent.model.mapper;

import com.ssafy.lirent.model.dto.SubleaseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubleaseMapper {
    public int insert(SubleaseDto newSublease);
    public List<SubleaseDto> selectAll();
}
