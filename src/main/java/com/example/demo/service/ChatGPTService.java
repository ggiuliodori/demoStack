package com.example.demo.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatGPTService {

  @Value("${chatgpt.api.url}")
  private String chatGptApiUrl;
  @Value("${chatgpt.api.key}")
  private String bearerToken;

  public String getChatGptSummary(String message) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(chatGptApiUrl);

    // Configurar el cuerpo de la solicitud con el mensaje recibido
    StringEntity stringEntity = new StringEntity("{\"text\": \"" + message + "\", \"model\": \"gpt-3.5-turbo\"}");
    httpPost.setEntity(stringEntity);
    httpPost.setHeader("Content-type", "application/json");
    httpPost.setHeader("Authorization", "Bearer " + bearerToken);

    // Realizar la solicitud HTTP
    CloseableHttpResponse response = httpClient.execute(httpPost);

    // Procesar la respuesta
    try {
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        return EntityUtils.toString(entity);
      } else {
        return null;
      }
    } finally {
      response.close();
    }
  }
}
