package com.ibrahim.shoppingcart.service.product;

import com.ibrahim.shoppingcart.dto.ProductDto;
import com.ibrahim.shoppingcart.model.Category;
import com.ibrahim.shoppingcart.model.Product;
import com.ibrahim.shoppingcart.request.AddProductRequest;
import com.ibrahim.shoppingcart.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long productId);
    void deleteProductById(Long id);
    Product updateProductById(ProductUpdateRequest request, Long productId);
    List<Product> getAllProducts();
    List<Product> getAllProductsByCategory(String category);
    List<Product> getAllProductsByBrand(String brand);
    List<Product> getAllProductsByCategoryAndBrand(Category category, String brand);
    List<Product> getAllProductsByName(String name);
    List<Product> getAllProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndNames(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);


    ProductDto convertToDto(Product product);
}
