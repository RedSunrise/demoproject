package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MockController {
    @GetMapping("/path")
    public ResponseEntity someMethod(){
        Object responseJson = "{parameter: value}";
        return ResponseEntity.ok(responseJson);
    }
}
