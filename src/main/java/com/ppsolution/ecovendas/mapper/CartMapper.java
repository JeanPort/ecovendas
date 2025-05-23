package com.ppsolution.ecovendas.mapper;

import com.ppsolution.ecovendas.dto.response.CartResponse;
import com.ppsolution.ecovendas.model.Cart;

public interface CartMapper {

    CartResponse toCartResponse(Cart cart);
}
