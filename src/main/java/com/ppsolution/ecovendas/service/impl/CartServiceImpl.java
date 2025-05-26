package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.CartItemRequest;
import com.ppsolution.ecovendas.dto.response.CartResponse;
import com.ppsolution.ecovendas.exception.CartNotFoundException;
import com.ppsolution.ecovendas.exception.ProductNotFoundException;
import com.ppsolution.ecovendas.mapper.CartItemMapper;
import com.ppsolution.ecovendas.mapper.CartMapper;
import com.ppsolution.ecovendas.model.Cart;
import com.ppsolution.ecovendas.repository.CartRepository;
import com.ppsolution.ecovendas.service.CartService;
import com.ppsolution.ecovendas.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final CartRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final CartMapper mapper;

    public CartServiceImpl(UserService userService, CartRepository cartRepository, CartItemMapper cartItemMapper, CartMapper mapper) {
        this.userService = userService;
        this.cartRepository = cartRepository;

        this.cartItemMapper = cartItemMapper;
        this.mapper = mapper;
    }

    @Override
    public CartResponse getCart() {
        var user = userService.getAuthenticatedUser();
        var cart = cartRepository.buscarPorUsuarioComItensEProdutos(user.getId()).orElseThrow(CartNotFoundException::new);
        return mapper.toCartResponse(cart);
    }

    @Override
    public CartResponse addItem(CartItemRequest itemRequest) {

        var user = userService.getAuthenticatedUser();
        var cart = cartRepository.buscarPorUsuarioComItensEProdutos(user.getId()).orElse(null);

        if (cart == null){
            cart = new Cart(user);
        }

        var cartItem = cartItemMapper.toCartItem(itemRequest);
        cartItem.setCart(cart);
        cart.addItems(cartItem);
        cart = cartRepository.save(cart);
        return mapper.toCartResponse(cart);
    }



    @Override
    public CartResponse clearCart() {
        var user = userService.getAuthenticatedUser();
        var cart = cartRepository.buscarPorUsuarioComItensEProdutos(user.getId()).orElseThrow(CartNotFoundException::new);
        cart.getItems().clear();
        cart = cartRepository.save(cart);
        return mapper.toCartResponse(cart);
    }

    @Override
    public CartResponse updateItemCarrinho(Long itemId, CartItemRequest itemRequest) {
        var user = userService.getAuthenticatedUser();
        var cart = cartRepository.buscarPorUsuarioComItensEProdutos(user.getId()).orElseThrow(CartNotFoundException::new);
        var itemUpdate = cart.getItems().stream().filter(item -> item.getId().equals(itemId)).findFirst().orElseThrow(ProductNotFoundException::new);
        itemUpdate.setQuantity(itemRequest.quantity());
        cart = cartRepository.save(cart);
        return mapper.toCartResponse(cart);
    }
}
