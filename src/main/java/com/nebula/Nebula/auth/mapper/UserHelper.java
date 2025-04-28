package com.nebula.Nebula.auth.mapper;

import com.nebula.Nebula.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    @Autowired
    private UserRepo userRepo;

    public String generateUserId() {
        String lastUserId = userRepo.findLastUserId(); // Fetch the last userId from DB

        int nextIdNumber = 11111; // Default starting number
        if (lastUserId != null && lastUserId.startsWith("Nebu")) {
            String numberPart = lastUserId.substring(4); // ✅ Correct: "Nebu" is 4 characters
            try {
                nextIdNumber = Integer.parseInt(numberPart) + 1;
            } catch (NumberFormatException e) {
                // In case parsing fails, fallback to default
                nextIdNumber = 11111;
            }
        }

        return "Nebu" + nextIdNumber;
    }
}
