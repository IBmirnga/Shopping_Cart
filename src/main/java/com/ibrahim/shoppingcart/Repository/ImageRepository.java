package com.ibrahim.shoppingcart.Repository;

import com.ibrahim.shoppingcart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
