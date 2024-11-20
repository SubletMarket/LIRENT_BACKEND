package com.ssafy.lirent.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.lirent.model.dto.BasketDto;
import com.ssafy.lirent.service.BasketService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    // 즐겨찾기 등록
    @PostMapping
    @Operation(summary = "즐겨찾기 등록")
    public ResponseEntity<?> addBasket(@RequestBody BasketDto basket, HttpServletRequest request) {
        int memberId = (Integer) request.getAttribute("memberId");

        basket.setMemberId(memberId);
        basketService.insertBasket(basket);
        return ResponseEntity.status(HttpStatus.CREATED).body("Basket item added successfully");
    }

    // 즐겨찾기 삭제
    @DeleteMapping("/{subleaseId}")
    @Operation(summary = "즐겨찾기 삭제")
    public ResponseEntity<?> removeBasket(@PathVariable int subleaseId, HttpServletRequest request) {
        int memberId = (Integer) request.getAttribute("memberId");

        basketService.deleteBasket(memberId, subleaseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 모든 즐겨찾기 조회
    @GetMapping
    @Operation(summary = "모든 즐겨찾기 조회")
    public ResponseEntity<List<BasketDto>> getAllBaskets(HttpServletRequest request) {
        int memberId = (int) request.getAttribute("memberId");

        List<BasketDto> baskets = basketService.getAllBaskets(memberId);
        return ResponseEntity.ok(baskets);
    }
}
