package com.ssafy.lirent.controller;

import com.ssafy.lirent.model.dto.sublease.SubleaseDealAddRequestDto;
import com.ssafy.lirent.model.dto.sublease.SubleaseDealGetResponseDto;
import com.ssafy.lirent.service.SubleaseDealService;
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
}
