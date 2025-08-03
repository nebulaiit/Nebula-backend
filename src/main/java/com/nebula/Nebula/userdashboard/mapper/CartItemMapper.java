package com.nebula.Nebula.userdashboard.mapper;


import com.nebula.Nebula.userdashboard.dto.CartItemDto;
import com.nebula.Nebula.userdashboard.model.CartItem;

public class CartItemMapper {

    public static CartItemDto toDto(CartItem cartItem) {
        return CartItemDto.builder()
                .cartItemId(cartItem.getId())
                .courseId(cartItem.getCourse().getId())
                .courseTitle(cartItem.getCourse().getCourseTitle())
                .thumbnailUrl(cartItem.getCourse().getThumbnailUrl())
                .price(cartItem.getCourse().getCoursePrice().getPrice())
                .discount(cartItem.getCourse().getCoursePrice().getDiscount())
                .effectivePrice(cartItem.getCourse().getCoursePrice().getEffectivePrice())
                .category(cartItem.getCourse().getCategory())
                .build();
    }
}
