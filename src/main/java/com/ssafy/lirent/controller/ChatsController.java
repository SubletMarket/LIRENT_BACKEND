package com.ssafy.lirent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ssafy.lirent.model.dto.ChatsDto;
import com.ssafy.lirent.service.ChatsService;

import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping("/api/chats")
public class ChatsController {

    private final ChatsService chatsService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatsController(ChatsService chatsService, SimpMessagingTemplate messagingTemplate) {
        this.chatsService = chatsService;
        this.messagingTemplate = messagingTemplate;
    }

    // 특정 sublease_id의 채팅 조회
    @GetMapping("/{subleaseId}")
    @Operation(summary = "채팅 조회")
    @ResponseBody
    public ResponseEntity<List<ChatsDto>> getChatsBySubleaseId(@PathVariable int subleaseId) {
        List<ChatsDto> chats = chatsService.getChatsBySubleaseId(subleaseId);
        return ResponseEntity.ok(chats);
    }

    // 채팅 작성
    @PostMapping
    @Operation(summary = "채팅 작성")
    @ResponseBody
    public ResponseEntity<?> writeChat(@RequestBody ChatsDto chat) {
        if (chat.getMessage() == null || chat.getMessage().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message is required.");
        }

        chatsService.insertChat(chat);
        return ResponseEntity.status(HttpStatus.CREATED).body(chat);
    }

    // 클라이언트가 웹소켓으로 데이터 전송
    @MessageMapping("/sendMessage/{subleaseId}")
    public void sendMessage(ChatsDto message, @DestinationVariable String subleaseId) {
        chatsService.insertChat(message);
        messagingTemplate.convertAndSend("/topic/chatroom/" + subleaseId, message);
    }

}
