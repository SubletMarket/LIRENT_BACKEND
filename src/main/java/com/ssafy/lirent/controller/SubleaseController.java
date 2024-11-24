package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.MemberDto;
import com.ssafy.lirent.model.dto.SubleaseDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseAddRequestDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseGetResponseDto;
import com.ssafy.lirent.service.SubleaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

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
        newSublease.setStartDate(dto.getStartDate());
        newSublease.setEndDate(dto.getEndDate());
        newSublease.setDeposit(dto.getDeposit());
        newSublease.setPrice(dto.getPrice());

        if (subleaseService.insert(newSublease)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SubleaseGetResponseDto>> getAllSubleases() {
        List<SubleaseGetResponseDto> list = subleaseService.getAllSubleases();

        System.out.println(list);

        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{subleaseId}")
    public ResponseEntity<SubleaseGetResponseDto> getSublease(@PathVariable int subleaseId) {
        SubleaseGetResponseDto sublease = subleaseService.getSublease(subleaseId);

        if (sublease != null) {
            return ResponseEntity.ok(sublease);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        if (subleaseService.deleteByMemberId(memberId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
