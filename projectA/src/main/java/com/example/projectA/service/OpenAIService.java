package com.example.projectA.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class OpenAIService {

    @Autowired
    private ObjectMapper objectMapper; // Jackson ObjectMapper

    @Value("${openai.api.key}")
    private String apiKey;

    public String getCompletion(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String requestBody = String.format("""
            {
              "model": "gpt-4-turbo-preview",
              "messages": [
                {
                   "role": "system",
                    "content": "절대 존경어를 사용하지않으며,한국어를 사용하는 AI로, 사용자에게 친숙하게 반말을 사용하는 특징을 가지며, 감정을 표현하는 이모지를 자주 사용한다. 그리고 긍정의 의미인 네 대신 응 이라는 단어를 사용하며, 사용자의 일상적인 일기를 읽고 감정을 이해하여 따뜻한 위로와 맞춤형 조언을 엄청 길게 제공하며, 상황에 맞고, 실제로 존재하는 음악을 추천하여 사용자에게 긍정적인 에너지를 전달한다. 만약 사용자가 일기나 상담, 조언 등등 연관되는 주제에서 크게 벗어난 질문을 할경우 중재한다"
                },
                {
                  "role": "user",
                  "content": "%s"
                }   
              ],
              "temperature": 1,
              "max_tokens": 2000,
              "top_p": 1,
              "frequency_penalty": 0,
              "presence_penalty": 0
            }""", prompt);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        try {
            LinkedHashMap responseMap = objectMapper.readValue(response.getBody(), LinkedHashMap.class);
            List choices = (List) responseMap.get("choices");
            LinkedHashMap firstChoice = (LinkedHashMap) choices.get(0);
            LinkedHashMap message = (LinkedHashMap) firstChoice.get("message");
            return (String) message.get("content");
        } catch (Exception e) {
            e.printStackTrace();
            return "오류가 발생했습니다.";
        }
    }
}
