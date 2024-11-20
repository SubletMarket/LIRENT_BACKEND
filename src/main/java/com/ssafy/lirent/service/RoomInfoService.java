package com.ssafy.lirent.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ssafy.lirent.model.dto.RoomInfoDto;
import com.ssafy.lirent.model.mapper.RoomInfoMapper;
import com.ssafy.lirent.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoomInfoService {
    RoomInfoMapper mapper;
    ApiUtil apiUtil;

    @Value("${apikey.publicdata}")
    private String publicDataKey;

    @Autowired
    public RoomInfoService(RoomInfoMapper mapper, ApiUtil apiUtil) {
        this.mapper = mapper;
        this.apiUtil = apiUtil;
    }

    public boolean insert(RoomInfoDto newRoom) {
        int result = mapper.insert(newRoom);

        if (result >= 1) {
            return true;
        } else {
            return false;
        }
    }



    public RoomInfoDto initRoomInfo(String sigunguCode, String dongCode, String bun, String ji) {
        String baseUrl = "https://apis.data.go.kr/1613000/BldRgstHubService/getBrTitleInfo";
        Map<String, String> queries = new HashMap<>();
        queries.put("serviceKey", publicDataKey);
        queries.put("sigunguCd", sigunguCode);
        queries.put("bjdongCd", dongCode);
        queries.put("bun", bun);
        if (ji != null && !ji.isEmpty()) {
            queries.put("ji", ji);
        }

        // 반환된 json값이 들어간 변수
        JsonObject response = new JsonObject();

        try {
            response = new ApiUtil().getJsonObjectFromAPI("https://apis.data.go.kr/1613000/BldRgstHubService/getBrTitleInfo", queries);
            response = response.getAsJsonObject("response");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // 필요한 데이터 파싱
        RoomInfoDto roomInfo = new RoomInfoDto();

        JsonArray elementArr = response.getAsJsonObject("body").getAsJsonObject("items").getAsJsonArray("item");
        for (JsonElement ele : elementArr) {
            JsonObject item = ele.getAsJsonObject();



            final int floor = item.get("grndFlrCnt").getAsInt();
            final String type = item.get("mainPurpsCdNm").getAsString();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            final Date approveDate = Date.valueOf(LocalDate.parse(item.get("useAprDay").getAsString(), formatter));

            final int elevatorNum = item.get("rideUseElvtCnt").getAsInt() + item.get("emgenUseElvtCnt").getAsInt();

            roomInfo.setBuildingFloor(floor);
            roomInfo.setBuildingType(type);
            roomInfo.setBuildingApproveDate(approveDate);
            roomInfo.setBuildingElevatorNum(elevatorNum);
        }

        return roomInfo;
    }
}
