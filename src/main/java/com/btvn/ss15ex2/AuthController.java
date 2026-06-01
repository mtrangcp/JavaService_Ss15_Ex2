package com.btvn.ss15ex2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Map<String, String> mockDatabase = new HashMap<>();

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if (mockDatabase.containsKey(username)) {
            response.put("status", 409);
            response.put("message", "Username đã tồn tại trên hệ thống!");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        mockDatabase.put(username, password);

        response.put("status", 201);
        response.put("message", "Đăng ký tài khoản thành công!");
        response.put("data", "User: " + username);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if (!mockDatabase.containsKey(username) || !mockDatabase.get(username).equals(password)) {
            response.put("status", 401);
            response.put("message", "Tên đăng nhập hoặc mật khẩu không chính xác.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        response.put("status", 200);
        response.put("message", "Đăng nhập thành công!");
        response.put("data", "Token_Gia_Lap_JWT_123456");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}