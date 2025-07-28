package com.nebula.Nebula.aiservice.service;

import okhttp3.*;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class OllamaService {

    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private static final MediaType JSON = MediaType.parse("application/json");

    private final OkHttpClient client = new OkHttpClient();

    public String sendPromptToOllama(String prompt, String model) throws IOException {
        String requestBody = String.format("""
            {
              "model": "%s",
              "prompt": "%s"
            }
        """, model, prompt.replace("\"", "'"));

        Request request = new Request.Builder()
                .url(OLLAMA_URL)
                .post(RequestBody.create(requestBody, JSON))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Ollama call failed: " + response);
            }
            return response.body().string();
        }
    }

    public String queryModel(String model, String prompt) {
        String requestBody = String.format("""
        {
          "model": "%s",
          "prompt": "%s",
          "stream": false
        }
        """, model, prompt.replace("\"", ""));

        Request request = new Request.Builder()
                .url(OLLAMA_URL)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(requestBody, JSON))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Ollama call failed: " + response.code() + " " + response.message());
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error calling Ollama: " + e.getMessage();
        }
    }
}
