package com.example.demo.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.service.ChatGPTService;

@Service
public class KafkaConsumerService {
  @Autowired
  private ChatGPTService chatGPTService;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @KafkaListener(topics = "create-book")
  public void consumeMessage(String message) {
    try {
      String summary = chatGPTService.getChatGptSummary(message);
      System.out.println("Resumen del mensaje: " + summary);
      throw new IOException();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}