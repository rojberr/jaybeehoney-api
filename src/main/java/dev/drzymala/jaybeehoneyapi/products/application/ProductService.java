package dev.drzymala.jaybeehoneyapi.products.application;

import dev.drzymala.jaybeehoneyapi.products.application.port.ProductUseCase;
import dev.drzymala.jaybeehoneyapi.products.db.ProductJpaRepository;
import dev.drzymala.jaybeehoneyapi.products.domain.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductJpaRepository repository;

    @Override
    public List<Product> findAll() {

        return repository.findAll();
    }

    @Override
    public List<Product> findByProductName(String productName) {

        return repository.findByProductName(productName);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product addProduct(CreateProductCommand command) {
        Product honey = toProduct(command);
        return repository.save(honey);
    }

    private Product toProduct(CreateProductCommand command) {
        Product honey = new Product(command.getName());
        return honey;
    }
}
