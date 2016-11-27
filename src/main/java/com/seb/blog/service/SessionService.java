package com.seb.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionService {
    private Map<String, String> userSessionMap = new HashMap<>();

    public void putSession(String sessionId, String userId) {
        userSessionMap.putIfAbsent(sessionId, userId);
    }

    public void removeSession(String sessionId) {
        userSessionMap.remove(sessionId);
    }

    public void logout(String sessionId) {
        userSessionMap.remove(sessionId);
    }

    public boolean isLogined(String sessionId) {
        if (userSessionMap.get(sessionId) == null ||
                userSessionMap.get(sessionId).equals("")) {
            return false;
        }
        return true;
    }

    public boolean isAdmin(String sessionId) {
        if (userSessionMap.get(sessionId) != null &&
                userSessionMap.get(sessionId).equals("admin")) {
            return true;
        }
        return false;
    }

    public String getUserId(String sessionId) {
        return userSessionMap.get(sessionId);
    }

}
