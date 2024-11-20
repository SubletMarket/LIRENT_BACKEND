package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.MemberDto;
import com.ssafy.lirent.model.dto.SubleaseDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseAddRequestDto;
import com.ssafy.lirent.service.SubleaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/api/sublease")
public class SubleaseController {
    SubleaseService subleaseService;

    @Autowired
    public SubleaseController(SubleaseService subleaseService) {
        this.subleaseService = subleaseService;
    }

    @PostMapping
    public ResponseEntity<Void> addSublease(@RequestBody SubleaseAddRequestDto dto, HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        SubleaseDto newSublease = new SubleaseDto();
        newSublease.setMemberId(memberId);
        newSublease.setStartDate(Date.valueOf(dto.getStartDate()));
        newSublease.setEndDate(Date.valueOf(dto.getEndDate()));
        newSublease.setDeposit(dto.getDeposit());
        newSublease.setPrice(dto.getPrice());

        if (subleaseService.insert(newSublease)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
