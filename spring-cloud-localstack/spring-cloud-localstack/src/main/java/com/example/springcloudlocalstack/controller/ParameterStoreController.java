package com.example.springcloudlocalstack.controller;

import com.example.springcloudlocalstack.parameterstore.configuration.ParameterStoreConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parameterstore")
public class ParameterStoreController {

    private final ParameterStoreConfiguration configuration;

    @Autowired
    public ParameterStoreController(ParameterStoreConfiguration parameterStoreConfiguration) {
        this.configuration = parameterStoreConfiguration;
    }

    @GetMapping("/name")
    public String getNameConfiguration() {
        return configuration.getName();
    }

    @GetMapping("/days")
    public String gatDaysConfiguration() {
        return configuration.getDays();
    }

}