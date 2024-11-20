package com.ssafy.lirent.interceptor;

import com.ssafy.lirent.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * JWT 인증 처리 Interceptor
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    JwtUtil jwtUtil;

    @Autowired
    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();

        // 경로 예외 설정
        if ("/api/member".equals(uri) && "POST".equalsIgnoreCase(method)) {
            return true;
        } else if ("/api/member/token".equalsIgnoreCase(uri)) {
            return true;
        }

        // JWT 토큰 유효성 여부 확인 및 처리
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7);

            // 토큰 검증
            if (jwtUtil.checkToken(accessToken)) {
                request.setAttribute("memberId", jwtUtil.getMemberId(accessToken));
                return true;
            }
        }

        // 로그인 안되있을 경우
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("Not Logined");
        return false;
    }
}