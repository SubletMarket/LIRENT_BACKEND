package com.ssafy.lirent.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.lirent.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
    void insertNotice(NoticeDto notice);
    void updateNotice(NoticeDto notice);
    void deleteNotice(int id);
    NoticeDto selectNoticeById(int id);
    List<NoticeDto> selectAllNotices();
}