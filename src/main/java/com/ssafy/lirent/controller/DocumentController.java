package com.ssafy.lirent.controller;

import com.ssafy.lirent.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/contract")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateContract(@RequestBody Map<String, String> userInputs) {
        System.out.println("generateContract 메서드 호출됨"); // 로그 추가

        try {
            String filePath = documentService.generateContract(userInputs);
            return ResponseEntity.ok("계약서가 PDF로 생성되었습니다: " + filePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("계약서 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
