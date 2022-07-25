package dev.drzymala.jaybeehoneyapi.products.db;

import dev.drzymala.jaybeehoneyapi.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findByProductName(String productName);

    Optional<Product> findDistinctFirstByProductNameStartsWithIgnoreCase(String productName);
}
