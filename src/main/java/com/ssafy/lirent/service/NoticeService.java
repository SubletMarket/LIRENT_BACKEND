package com.ssafy.lirent.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.lirent.model.NoticeDto;
import com.ssafy.lirent.model.mapper.NoticeMapper;
//import com.ssafy.lirent.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;
    
    @Transactional
    public void insertNotice(NoticeDto notice) {
        noticeMapper.insertNotice(notice);
    }

    public void updateNotice(NoticeDto notice) {
        noticeMapper.updateNotice(notice);
    }

    public void deleteNotice(int id) {
        noticeMapper.deleteNotice(id);
    }

    public NoticeDto getNoticeById(int id) {
        return noticeMapper.selectNoticeById(id);
    }

    public List<NoticeDto> getAllNotices() {
        return noticeMapper.selectAllNotices();
    }
}
