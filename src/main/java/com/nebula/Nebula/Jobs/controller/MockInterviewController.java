package com.nebula.Nebula.Jobs.controller;


import com.nebula.Nebula.aiservice.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/interview")
public class MockInterviewController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/mock")
    public ResponseEntity<String> generateMockInterview(
            @RequestParam String jobTitle,
            @RequestParam(defaultValue = "5") int questionCount,
            @RequestParam(defaultValue = "medium") String difficulty,
            @RequestParam(defaultValue = "true") boolean includeCoding) {

        String prompt = buildPrompt(jobTitle, questionCount, difficulty, includeCoding);
        String response = ollamaService.queryModel("llama3", prompt);
        return ResponseEntity.ok(response);
    }

    private String buildPrompt(String jobTitle, int questionCount, String difficulty, boolean includeCoding) {
        return """
        You are a senior technical interviewer.

        Job Role: %s  
        Number of Questions: %d  
        Difficulty: %s  
        Include Coding Questions: %s

        ➤ First, list all interview questions (without answers).  
        ➤ After listing all questions, provide model answers grouped below.  
        ➤ Then provide **overall feedback** at the end (suggestions on areas to improve based on the questions).

        Format your response like this:

        1. 📋 Questions
        2. ✅ Answers
        3. 📌 Feedback

        Keep the tone helpful and professional.
        """.formatted(
                jobTitle,
                questionCount,
                difficulty,
                includeCoding ? "Yes" : "No"
        );
    }
}
