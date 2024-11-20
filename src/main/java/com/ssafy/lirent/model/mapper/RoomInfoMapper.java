package com.ssafy.lirent.model.mapper;

import com.ssafy.lirent.model.dto.RoomInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomInfoMapper {
    public int insert(RoomInfoDto dto);
    public RoomInfoDto getRoomInfoByRoomId(int roomId);
    public int update(RoomInfoDto dto);
    public int delete(int roomId);
}
