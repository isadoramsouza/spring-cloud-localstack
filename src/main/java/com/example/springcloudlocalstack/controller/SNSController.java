package com.example.springcloudlocalstack.controller;

import com.example.springcloudlocalstack.service.SNSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sns")
public class SNSController {

    private final SNSService snsService;

    @Autowired
    public SNSController(SNSService snsService) {
        this.snsService = snsService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message) {
        snsService.sendNotification(message, null);
        return ResponseEntity.ok().build();
    }
}