package com.ssafy.lirent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.lirent.model.dto.ChatsDto;
import com.ssafy.lirent.service.ChatsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/chats")
public class ChatsController {

    @Autowired
    private ChatsService chatsService;

    // 특정 sublease_id의 채팅 조회
    @GetMapping("/{subleaseId}")
    @Operation(summary = "채팅 조회")
    public ResponseEntity<List<ChatsDto>> getChatsBySubleaseId(@PathVariable int subleaseId) {
        List<ChatsDto> chats = chatsService.getChatsBySubleaseId(subleaseId);
        return ResponseEntity.ok(chats);
    }

    // 채팅 작성
    @PostMapping
    @Operation(summary = "채팅 작성")
    public ResponseEntity<?> writeChat(@RequestBody ChatsDto chat) {
        if (chat.getMessage() == null || chat.getMessage().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message is required.");
        }

        chatsService.insertChat(chat);
        return ResponseEntity.status(HttpStatus.CREATED).body(chat);
    }
}
