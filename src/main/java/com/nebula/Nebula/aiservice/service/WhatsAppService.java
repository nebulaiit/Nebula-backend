package com.nebula.Nebula.aiservice.service;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WhatsAppService {

    @Value("${whatsapp.phone.number.id}")
    private String phoneNumberId;

    @Value("${whatsapp.token}")
    private String accessToken;

    private final OkHttpClient client = new OkHttpClient();

    public String sendWhatsAppMessage(String toPhoneNumber, String messageText) throws IOException {
        String url = "https://graph.facebook.com/v19.0/" + phoneNumberId + "/messages";

        MediaType mediaType = MediaType.parse("application/json");

        String payload = """
                {
                  "messaging_product": "whatsapp",
                  "to": "%s",
                  "type": "text",
                  "text": {
                    "preview_url": false,
                    "body": "%s"
                  }
                }
                """.formatted(toPhoneNumber, messageText);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(payload, mediaType))
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return "Message sent successfully!";
            } else {
                return "Failed: " + response.body().string();
            }
        }
    }
}
