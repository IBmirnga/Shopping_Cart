package com.ibrahim.shoppingcart.controller;

import com.ibrahim.shoppingcart.exceptions.ResourceNotFoundException;
import com.ibrahim.shoppingcart.model.Product;
import com.ibrahim.shoppingcart.request.AddProductRequest;
import com.ibrahim.shoppingcart.request.ProductUpdateRequest;
import com.ibrahim.shoppingcart.response.ApiResponse;
import com.ibrahim.shoppingcart.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Success", products));
    }

    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product products = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Success", products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        //return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Add product successfully!", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId) {
        try {
            Product theProduct = productService.updateProductById(request, productId);
            return ResponseEntity.ok(new ApiResponse("Updated product successfully!", theProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Deleted product successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getALLProductsByBrandAndName(@PathVariable String brandName, @PathVariable String productName) {
        try {
            List<Product> products = productService.getAllProductsByBrandAndName(brandName, productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success!", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/category-and-name")
    public ResponseEntity<ApiResponse> getALLProductsByCategoryAndBrand(@PathVariable String category, @PathVariable String brand) {
        try {
            List<Product> products = productService.getAllProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success!", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/name")
    public ResponseEntity<ApiResponse> getALLProductsByName(@PathVariable String name) {
        try {
            List<Product> products = productService.getAllProductsByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success!", products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> getALLProductsByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getAllProductsByBrand(brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success!", products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> getALLProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getAllProductsByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("No products found", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success!", products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndNames(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = productService.countProductsByBrandAndNames(brand, name);
            return ResponseEntity.ok(new ApiResponse("Product count!", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }
}
