package com.example.springcloudlocalstack.controller;

import com.example.springcloudlocalstack.service.SQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SQSController {

    private final SQSService sqsService;

    @Autowired
    public SQSController(SQSService sqsService) {
        this.sqsService = sqsService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message) {
        sqsService.sendMessage(message);
        return ResponseEntity.ok().build();
    }
}