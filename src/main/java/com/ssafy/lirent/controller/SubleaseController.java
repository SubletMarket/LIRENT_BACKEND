package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.RoomInfoDto;
import com.ssafy.lirent.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sublease")
public class SubleaseController {
//    RoomInfoService roomInfoService;
//
//    @Autowired
//    public SubleaseController(RoomInfoService roomInfoService) {
//        this.roomInfoService = roomInfoService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> createRoom(String sidoCode, String dongCode, String bun, String ji) {
//        RoomInfoDto newRoom = roomInfoService.initRoomInfo(sidoCode, dongCode, bun, ji);
//
//        System.out.println(newRoom);
//
//        return ResponseEntity.ok().build();
//    }
}
