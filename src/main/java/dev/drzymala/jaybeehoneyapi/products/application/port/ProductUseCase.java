package dev.drzymala.jaybeehoneyapi.products.application.port;

import dev.drzymala.jaybeehoneyapi.products.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductUseCase {

    public List<Product> findAll();

    public List<Product> findByProductName(String productName);

    Optional<Product> findById(Long id);
}
