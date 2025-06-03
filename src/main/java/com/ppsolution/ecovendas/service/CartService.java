package com.ppsolution.ecovendas.service;

import com.ppsolution.ecovendas.dto.request.CartItemRequest;
import com.ppsolution.ecovendas.dto.response.CartResponse;

public interface CartService {

    CartResponse getCart();
    CartResponse clearCart();
    CartResponse addItem(CartItemRequest itemRequest);
    CartResponse updateQuantityItem(Long idItem, CartItemRequest itemRequest);

}
