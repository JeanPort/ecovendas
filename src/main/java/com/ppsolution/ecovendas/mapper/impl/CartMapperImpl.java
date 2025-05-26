package com.ppsolution.ecovendas.mapper.impl;

import com.ppsolution.ecovendas.dto.response.CartItemResponse;
import com.ppsolution.ecovendas.dto.response.CartResponse;
import com.ppsolution.ecovendas.mapper.CartItemMapper;
import com.ppsolution.ecovendas.mapper.CartMapper;
import com.ppsolution.ecovendas.model.Cart;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartMapperImpl implements CartMapper {

    private final CartItemMapper itemMapper;

    public CartMapperImpl(CartItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public CartResponse toCartResponse(Cart cart) {
        if (cart == null){
            return null;
        }

        var totalItems = 0;
        var totalPrice = BigDecimal.ZERO;
        var itemsResponse = itemMapper.toListCartItemResponse(cart.getItems());

        for (CartItemResponse cartItemResponse : itemsResponse) {
            totalItems += cartItemResponse.quantity();
            totalPrice = totalPrice.add(cartItemResponse.subTotal());
        }

        return new CartResponse(
                cart.getId(),
                itemsResponse,
                totalItems,
                totalPrice,
                cart.getCreatedAt(),
                cart.getUpdatedAt()
        );
    }
}
