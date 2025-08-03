package com.nebula.Nebula.userdashboard.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    private UUID cartItemId;

    private UUID courseId;
    private String courseTitle;
    private String thumbnailUrl;
    private double price;
    private double discount;
    private double effectivePrice;
    private String category;
}

