package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.CartItemRequest;
import com.ppsolution.ecovendas.dto.response.CartResponse;
import com.ppsolution.ecovendas.exception.CartNotFoundException;
import com.ppsolution.ecovendas.exception.ProductNotFoundException;
import com.ppsolution.ecovendas.mapper.CartItemMapper;
import com.ppsolution.ecovendas.mapper.CartMapper;
import com.ppsolution.ecovendas.model.Cart;
import com.ppsolution.ecovendas.model.CartItem;
import com.ppsolution.ecovendas.repository.CartRepository;
import com.ppsolution.ecovendas.repository.ProductRepository;
import com.ppsolution.ecovendas.service.CartService;
import com.ppsolution.ecovendas.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final CartRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final CartMapper mapper;
    private final ProductRepository productRepository;

    public CartServiceImpl(UserService userService, CartRepository cartRepository, CartItemMapper cartItemMapper, CartMapper mapper, ProductRepository productRepository) {
        this.userService = userService;
        this.cartRepository = cartRepository;

        this.cartItemMapper = cartItemMapper;
        this.mapper = mapper;
        this.productRepository = productRepository;
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
        var product = productRepository.findById(itemRequest.productId()).orElseThrow(ProductNotFoundException::new);

        if (cart == null){
            cart = new Cart(user);
        }

        var oprionalItem = buscarItemByProductId(itemRequest.productId(), cart.getItems());


        if (oprionalItem.isPresent()){
            var item = oprionalItem.get();
            item.setQuantity(item.getQuantity() + itemRequest.quantity());
            item.setUpdatedAt(LocalDateTime.now());
            cart.setUpdatedAt(LocalDateTime.now());
            cart = cartRepository.save(cart);
            return mapper.toCartResponse(cart);
        }

        var cartItem = cartItemMapper.toCartItem(itemRequest, cart, product);
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

    private static Optional<CartItem> buscarItemByProductId(Long productId, List<CartItem> items) {
        return items
                .stream()
                .filter(cItem -> cItem.getProduct().getId().equals(productId))
                .findFirst();
    }
}
