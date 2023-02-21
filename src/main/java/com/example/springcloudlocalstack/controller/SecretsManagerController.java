package com.example.springcloudlocalstack.controller;

import com.example.springcloudlocalstack.parameterstore.configuration.ParameterStoreConfiguration;
import com.example.springcloudlocalstack.secretsmanager.configuration.SecretsManagerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secretsmanager")
public class SecretsManagerController {

    private final SecretsManagerConfiguration configuration;

    @Autowired
    public SecretsManagerController(SecretsManagerConfiguration secretsManagerConfiguration) {
        this.configuration = secretsManagerConfiguration;
    }

    @GetMapping("/configuration")
    public String configuration() {
        return String.format("%s - %s - %s", configuration.getValue1(),
                configuration.getValue2(), configuration.getValue3());
    }

}