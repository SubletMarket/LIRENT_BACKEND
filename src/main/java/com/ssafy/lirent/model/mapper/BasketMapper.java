package com.ssafy.lirent.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ssafy.lirent.model.dto.BasketDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseGetResponseDto;


@Mapper
public interface BasketMapper {
    void insertBasket(BasketDto basket);
    void deleteBasket(@Param("memberId") int memberId, @Param("subleaseId") int subleaseId);
    List<SubleaseGetResponseDto> selectAllBaskets(@Param("memberId") int memberId);
}