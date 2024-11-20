package com.ssafy.lirent.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.lirent.model.dto.ChatsDto;
import com.ssafy.lirent.model.mapper.ChatsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatsService {

    private final ChatsMapper chatsMapper;

    @Transactional
    public void insertChat(ChatsDto chat) {
        chatsMapper.insertChat(chat);
    }

    public List<ChatsDto> getChatsBySubleaseId(int subleaseId) {
        return chatsMapper.selectChatsBySubleaseId(subleaseId);
    }
}