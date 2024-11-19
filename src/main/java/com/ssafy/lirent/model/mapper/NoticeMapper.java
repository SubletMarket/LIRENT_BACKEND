package com.ssafy.lirent.model.mapper;

import java.util.List;
import com.ssafy.lirent.model.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    void insertNotice(NoticeDto notice);
    void updateNotice(NoticeDto notice);
    void deleteNotice(int id);
    NoticeDto selectNoticeById(int id);
    List<NoticeDto> selectAllNotices();
}