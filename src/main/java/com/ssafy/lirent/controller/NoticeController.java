package com.ssafy.lirent.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.lirent.model.dto.NoticeDto;
import com.ssafy.lirent.model.dto.MemberDto;
import com.ssafy.lirent.service.NoticeService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 전체 공지사항 조회
    @GetMapping
    @Operation(summary = "게시판 전체 조회")
    public ResponseEntity<List<NoticeDto>> getNoticeList() {
    	System.out.println("asdf");
        List<NoticeDto> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

    // 공지사항 작성
    @PostMapping
    @Operation(summary = "게시판 작성")
    public ResponseEntity<?> writeNotice(@RequestBody NoticeDto notice, HttpSession session) {
    	MemberDto member = (MemberDto) session.getAttribute("member");
    	
        if (notice.getTitle() == null || notice.getTitle().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title is required.");
        } else if (member == null) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logined.");
        }

        notice.setMemberId(member.getMemberId());
        noticeService.insertNotice(notice);
        return ResponseEntity.status(HttpStatus.CREATED).body(notice);
    }

    // 특정 공지사항 조회
    @GetMapping("/{id}")
    @Operation(summary = "게시판 조회")
    public ResponseEntity<?> getNoticeById(@PathVariable int id) {
        NoticeDto notice = noticeService.getNoticeById(id);
        if (notice != null) {
            return ResponseEntity.ok(notice);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notice not found");
        }
    }

    // 공지사항 수정
    @PutMapping("/{id}")
    @Operation(summary = "게시판 수정")
    public ResponseEntity<?> editNotice(@PathVariable int id, @RequestBody NoticeDto notice) {
    	NoticeDto existingNotice = noticeService.getNoticeById(id);
        if (existingNotice == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notice not found");
        }
        notice.setBoardId(id);
        noticeService.updateNotice(notice);
        return ResponseEntity.ok(notice);
    }

    // 공지사항 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "게시판 제거")

    public ResponseEntity<?> deleteNotice(@PathVariable int id) {
    	NoticeDto existingNotice = noticeService.getNoticeById(id);
        if (existingNotice == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notice not found");
        }
        noticeService.deleteNotice(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}