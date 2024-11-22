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
        // 기준 경로
        final String BASE_PATH = "C:\\Users\\SSAFY\\Desktop\\Final_project\\LIRENT_BACKEND\\src\\main\\resources\\contract\\ContractResults";
        
        System.out.println("??S");
        try {
            // 사용자 입력 경로를 기준 경로와 결합하여 안전한 파일 경로 생성
            File file = new File(BASE_PATH, new File(filePath).getName());

            // 파일 존재 여부 확인
            if (!file.exists() || !file.isFile()) {
                return ResponseEntity.notFound().build();
            }

            // 파일 시스템 리소스 생성
            FileSystemResource resource = new FileSystemResource(file);

            // 파일 이름을 인코딩하여 브라우저 호환성 확보
            String encodedFileName = java.net.URLEncoder.encode(file.getName(), "UTF-8").replace("+", "%20");

            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName);

            // 응답 반환
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            // 에러 처리
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
