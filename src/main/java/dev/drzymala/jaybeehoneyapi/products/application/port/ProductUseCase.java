package dev.drzymala.jaybeehoneyapi.products.application.port;

import dev.drzymala.jaybeehoneyapi.products.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface ProductUseCase {

    public List<Product> findAll();

    Optional<Product> findById(Long id);

    public List<Product> findByProductName(String productName);

    public Optional<Product> findOneByProductName(String productName);

    UpdateProductResponse updateProduct(UpdateProductCommand command);

    Product addProduct(CreateProductCommand command);

    @Value
    class CreateProductCommand {
        String name;
    }

    @Value
    @Builder
    @AllArgsConstructor
    class UpdateProductCommand {

        Long id;
        String name;
    }

    @Value
    class UpdateProductResponse {

        public static UpdateProductResponse SUCCESS = new UpdateProductResponse(true, emptyList());
        boolean success;
        List<String> errors;
    }
}
