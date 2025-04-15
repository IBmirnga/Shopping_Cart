package com.ibrahim.shoppingcart.Repository;

import com.ibrahim.shoppingcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}

