package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.sublease.SubleaseDealAddRequestDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseDealGetResponseDto;
import com.ssafy.lirent.service.MemberService;
import com.ssafy.lirent.service.SubleaseDealService;
import com.ssafy.lirent.service.SubleaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deal")
public class SubleaseDealController {
    SubleaseDealService service;

    @Autowired
    public SubleaseDealController(SubleaseDealService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SubleaseDealGetResponseDto>> getSubleaseDealByMemberId(HttpServletRequest request) {
        Integer memberId = (Integer) request.getAttribute("memberId");

        if (memberId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            List<SubleaseDealGetResponseDto> list = service.getDealsByMemberId(memberId);
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("{subleaseId}")
    public ResponseEntity<List<SubleaseDealGetResponseDto>> getSubleaseDealBySubleaseId(@PathVariable int subleaseId) {
        List<SubleaseDealGetResponseDto> list = service.getDealsBySubleaseId(subleaseId);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> makeSubleaseDeal(@RequestBody SubleaseDealAddRequestDto dto, HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        dto.setContractorId(memberId);

        return service.insert(dto) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/accept")
    public ResponseEntity<Void> acceptSubleaseDeal(@RequestBody Integer dealId, HttpServletRequest request) {
        Integer memberId = (Integer) request.getAttribute("memberId");

        if (memberId == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean success = service.accept(dealId, memberId);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{dealId}")
    public ResponseEntity<Void> deleteSubleaseDeal(@PathVariable int dealId, HttpServletRequest requset) {
        Integer memberId = (Integer) requset.getAttribute("memberId");

        if (service.delete(dealId, memberId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
