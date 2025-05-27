package com.ppsolution.ecovendas.mapper.impl;

import com.ppsolution.ecovendas.dto.request.CartItemRequest;
import com.ppsolution.ecovendas.dto.response.CartItemResponse;
import com.ppsolution.ecovendas.exception.ProductNotFoundException;
import com.ppsolution.ecovendas.mapper.CartItemMapper;
import com.ppsolution.ecovendas.model.Cart;
import com.ppsolution.ecovendas.model.CartItem;
import com.ppsolution.ecovendas.model.Product;
import com.ppsolution.ecovendas.model.User;
import com.ppsolution.ecovendas.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Override
    public CartItem toCartItem(CartItemRequest request, Cart cart, Product product) {
        if (request == null) return null;

        var item = new CartItem();
        item.setQuantity(request.quantity());
        item.setProduct(product);
        item.setPrice(product.getPrice());
        item.setCart(cart);
        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());
        return item;
    }


}
