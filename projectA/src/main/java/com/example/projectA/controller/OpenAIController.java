package com.example.projectA.controller;

import com.example.projectA.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/completion")
    public String getCompletion(@RequestBody String prompt) {
        return openAIService.getCompletion(prompt);
    }
}
