package com.nebula.Nebula.Jobs.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebula.Nebula.Jobs.dto.ATSResponse;

import com.nebula.Nebula.aiservice.service.OllamaService;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class ATSController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/ats-score")
    public ResponseEntity<ATSResponse> checkResume(
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("jobTitle") String jobTitle) {

        try {
            String resumeText = new Tika().parseToString(resume.getInputStream());
            String prompt = buildPrompt(resumeText, jobTitle);
            String response = ollamaService.sendPromptToOllama(prompt, "mistral"); // or "llama3"
            ATSResponse ats = extractScoreAndFeedback(response);

            return ResponseEntity.ok(ats);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ATSResponse(0, "❌ ATS check failed: " + e.getMessage()));
        }
    }

    private String buildPrompt(String resumeText, String jobTitle) {
        return """
            You are an AI resume screening expert.

            Evaluate this resume for the job title: "%s".

            Resume:
            %s

            Respond in this format:
            Score: <number>/100
            Feedback: <detailed feedback>
            """.formatted(jobTitle, resumeText);
    }

    private ATSResponse extractScoreAndFeedback(String rawJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(rawJson);
        String content = root.path("response").asText();

        int score = 0;
        Matcher matcher = Pattern.compile("Score:\\s*(\\d{1,3})").matcher(content);
        if (matcher.find()) {
            score = Integer.parseInt(matcher.group(1));
        }

        String feedback = content.replaceAll("Score:\\s*\\d{1,3}\\s*/\\s*100", "").trim();
        return new ATSResponse(score, feedback);
    }
}
