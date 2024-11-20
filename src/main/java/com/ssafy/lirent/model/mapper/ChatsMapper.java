package com.ssafy.lirent.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.lirent.model.dto.ChatsDto;

@Mapper
public interface ChatsMapper {
    void insertChat(ChatsDto chat);
    List<ChatsDto> selectChatsBySubleaseId(int subleaseId);
}
