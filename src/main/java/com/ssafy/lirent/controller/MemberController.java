package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    MemberMapper mapper;

    @GetMapping
    public ResponseEntity<Integer> getMember() {
        return ResponseEntity.ok(mapper.sel());
    }
}
