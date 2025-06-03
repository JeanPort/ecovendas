package com.ppsolution.ecovendas.controller;

import com.ppsolution.ecovendas.dto.request.CartItemRequest;
import com.ppsolution.ecovendas.dto.response.CartResponse;
import com.ppsolution.ecovendas.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {

    private final CartService service;

    public CartRestController(CartService service) {
        this.service = service;
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItem(@Valid @RequestBody CartItemRequest request){
        var cart = service.addItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @DeleteMapping("/items/clear")
    public ResponseEntity<CartResponse> clear(){
        var cartResponse = service.clearCart();
        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCartByUser(){
        var cart = service.getCart();
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateQuantityItem(@PathVariable(name = "itemId") Long itemId, @Valid @RequestBody CartItemRequest request){
        var cart = service.updateQuantityItem(itemId, request);
        return ResponseEntity.ok(cart);
    }
}
