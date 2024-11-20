package com.ssafy.lirent.util;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApiUtil {
//    @Value("${apikey.publicdata}")
//    private static String publicDataKey = "MM81EichsJiGtouDPiizLFgSg+yHbNkCQS9nXdnnSI5LQU/2yC5OHD5uJttpQL8meO26Qrujb5E2VPwvaDDhUA==";
//
//
//    public static void main(String[] args) {
//        Map<String, String> queries = new HashMap<>();
//        queries.put("serviceKey", publicDataKey);
//        queries.put("sigunguCd", "30200");
//        queries.put("bjdongCd", "11300");
//        queries.put("bun", "0517");
//        queries.put("ji", "0005");
//
//        JsonObject response = new JsonObject();
//        try {
//            response = new ApiUtil().getJsonObjectFromAPI("https://apis.data.go.kr/1613000/BldRgstHubService/getBrTitleInfo", queries);
//            response = response.getAsJsonObject("response");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        JsonArray elementArr = response.getAsJsonObject("body").getAsJsonObject("items").getAsJsonArray("item");
//
//        for (JsonElement ele : elementArr) {
//            JsonObject item = ele.getAsJsonObject();
//            System.out.println("층수 :" + item.get("grndFlrCnt"));
//            System.out.println("구분 :" + item.get("mainPurpsCdNm"));
//            System.out.println("준공일자 :" + item.get("useAprDay"));
//            System.out.println("승강기 :" + item.get("rideUseElvtCnt"));
//            System.out.println("승강기 :" + item.get("emgenUseElvtCnt"));
//        }
//    }

    public JsonObject getJsonObjectFromAPI(String baseUrl, Map<String, String> queries) {
        // URL 빌더 초기화
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?");

        try {
            // 쿼리 파라미터 추가
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                urlBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            }

            // 기본 쿼리 파라미터 추가
            urlBuilder.append("_type").append("=").append("json").append("&");
            urlBuilder.append("numOfRows").append("=").append("1").append("&");
            urlBuilder.append("pageNo").append("=").append(1);

            // HTTP 요청 보내기
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            BufferedReader reader;
            if (responseCode >= 200 && responseCode < 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            // 응답 데이터 읽기
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();
            conn.disconnect();

            // JSON 문자열을 JsonObject로 변환
            return JsonParser.parseString(responseBuilder.toString()).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
