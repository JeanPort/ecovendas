package com.ppsolution.ecovendas.mapper.impl;

import com.ppsolution.ecovendas.dto.response.CartItemResponse;
import com.ppsolution.ecovendas.mapper.CartItemMapper;
import com.ppsolution.ecovendas.model.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartItemMapperImpl implements CartItemMapper {


    @Override
    public CartItemResponse toCartItemResponse(CartItem item) {
        if (item == null) return null;

        return new CartItemResponse(
                item.getId(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getPrice(),
                item.getQuantity(),
                item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())),
                item.getProduct().getUrlImage()
        );
    }

    @Override
    public List<CartItemResponse> toListCartItemResponse(List<CartItem> items) {
        if (items == null) return null;

        return items.stream().map(this::toCartItemResponse).toList();
    }


}
