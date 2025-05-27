package com.ppsolution.ecovendas.mapper;

import com.ppsolution.ecovendas.dto.request.CartItemRequest;
import com.ppsolution.ecovendas.dto.response.CartItemResponse;
import com.ppsolution.ecovendas.model.Cart;
import com.ppsolution.ecovendas.model.CartItem;
import com.ppsolution.ecovendas.model.Product;
import com.ppsolution.ecovendas.model.User;

import java.util.List;

public interface CartItemMapper {

    CartItemResponse toCartItemResponse(CartItem item);
    List<CartItemResponse> toListCartItemResponse(List<CartItem> items);
    CartItem toCartItem(CartItemRequest request, Cart cart, Product product);
}
