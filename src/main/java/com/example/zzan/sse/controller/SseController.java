package com.example.zzan.sse.controller;

import com.example.zzan.sse.service.SseService;
import com.example.zzan.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Tag(name = "SseController", description = "SSE 파트")
@RequestMapping("/api/")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class SseController {
    private final SseService sseService;

    @GetMapping(value = "/events/{userId}", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@PathVariable Long userId, HttpServletRequest request) {
        String accessToken = getAccessTokenFromCookie(request);
        return this.sseService.register(userId);
    }

    private String getAccessTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accessKey")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}