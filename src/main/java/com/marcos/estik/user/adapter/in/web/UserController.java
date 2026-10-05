package com.marcos.estik.user.adapter.in.web;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.estik.user.adapter.in.web.response.UserResponseSummary;

@RestController 
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public ResponseEntity<Page<UserResponseSummary>> getUsers(
        @RequestParam(defaultValue = "") String q,
        @RequestParam(defaultValue = "0") String page,
        @RequestParam(defaultValue = "20") String size
    ) {
        
    }
}
