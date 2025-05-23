package com.ppsolution.ecovendas.mapper;

import com.ppsolution.ecovendas.dto.response.CartItemResponse;
import com.ppsolution.ecovendas.model.CartItem;

import java.util.List;

public interface CartItemMapper {

    CartItemResponse toCartItemResponse(CartItem item);
    List<CartItemResponse> toListCartItemResponse(List<CartItem> items);
}
