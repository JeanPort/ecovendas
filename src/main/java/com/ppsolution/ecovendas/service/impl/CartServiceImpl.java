package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.CartItemRequest;
import com.ppsolution.ecovendas.dto.response.CartResponse;
import com.ppsolution.ecovendas.exception.CartItemNotFoundException;
import com.ppsolution.ecovendas.exception.ProductNotFoundException;
import com.ppsolution.ecovendas.mapper.CartItemMapper;
import com.ppsolution.ecovendas.mapper.CartMapper;
import com.ppsolution.ecovendas.model.Cart;
import com.ppsolution.ecovendas.model.CartItem;
import com.ppsolution.ecovendas.model.User;
import com.ppsolution.ecovendas.repository.CartRepository;
import com.ppsolution.ecovendas.repository.ProductRepository;
import com.ppsolution.ecovendas.service.CartService;
import com.ppsolution.ecovendas.util.SecurityUtil;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;
    private final CartMapper mapper;
    private final CartItemMapper itemMapper;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartMapper mapper, CartItemMapper itemMapper, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.mapper = mapper;
        this.itemMapper = itemMapper;
        this.productRepository = productRepository;
    }

    @Override
    public CartResponse getCart() {
        var cart = obterCartDoUserLogado();
        return mapper.toCartResponse(cart);
    }

    @Override
    public CartResponse clearCart() {
        var cart = obterCartDoUserLogado();
        cart.getItems().clear();
        cart.setUpdatedAt(LocalDateTime.now());
        cart = cartRepository.save(cart);
        return mapper.toCartResponse(cart);
    }

    @Override
    public CartResponse addItem(CartItemRequest itemRequest) {

        var cart = obterCartDoUserLogado();
        var objectItem = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(itemRequest.productId()))
                .findFirst();

        if (objectItem.isPresent()){
            var item = objectItem.get();
            item.setQuantity(itemRequest.quantity() + item.getQuantity());
            item.setUpdatedAt(LocalDateTime.now());
        }else {
            var produtct = productRepository.findById(itemRequest.productId()).orElseThrow(ProductNotFoundException::new);
            var item = itemMapper.toCartItem(itemRequest, cart, produtct);
            cart.addItems(item);
            cart.setUpdatedAt(LocalDateTime.now());
        }

        cart = cartRepository.save(cart);
        return mapper.toCartResponse(cart);
    }

    @Override
    public CartResponse updateQuantityItem(Long idItem, CartItemRequest itemRequest) {
        var cart = obterCartDoUserLogado();
        var item = getItemOrThrow(idItem, cart);
        item.setQuantity(itemRequest.quantity());
        cart.setUpdatedAt(LocalDateTime.now());
        cart = cartRepository.save(cart);
        return mapper.toCartResponse(cart);
    }

    private static CartItem getItemOrThrow(Long idItem, Cart cart) {
        return cart.getItems()
                .stream()
                .filter(i -> i.getId().equals(idItem))
                .findFirst()
                .orElseThrow(CartItemNotFoundException::new);
    }

    private Cart obterCartDoUserLogado() {
        var user = SecurityUtil.getUserAuthenticated();
        return cartRepository.buscarPorUsuarioComItensEProdutos(user.getId()).orElseGet(() -> criarCart(user));
    }

    private Cart criarCart(User user){
        var cart = new Cart(user);
        return cartRepository.save(cart);
    }
}
