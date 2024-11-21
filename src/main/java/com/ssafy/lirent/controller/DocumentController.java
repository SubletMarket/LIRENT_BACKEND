package com.ssafy.lirent.controller;

import com.ssafy.lirent.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/contract")
public class DocumentController {
	private static final String BASE_PATH = "src/main/resources/"; // 기준 경로

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
    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadContract(@RequestParam String filePath) {
        File file = new File(filePath);
        
        System.out.println(BASE_PATH);
        System.out.println(filePath);
        System.out.println(file);
        
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("tq");

        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        System.out.println(headers.getDate());
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
        
        
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
