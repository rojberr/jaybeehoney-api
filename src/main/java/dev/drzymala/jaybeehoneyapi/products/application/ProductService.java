package dev.drzymala.jaybeehoneyapi.products.application;

import dev.drzymala.jaybeehoneyapi.products.application.port.ProductUseCase;
import dev.drzymala.jaybeehoneyapi.products.db.ProductJpaRepository;
import dev.drzymala.jaybeehoneyapi.products.domain.Manufacturer;
import dev.drzymala.jaybeehoneyapi.products.domain.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Optional<Product> findById(Long id) {

        return repository.findById(id);
    }

    @Override
    public List<Product> findByProductName(String productName) {

        return repository.findByProductName(productName);
    }

    @Override
    public Optional<Product> findOneByProductName(String productName) {

        return repository.findDistinctFirstByProductNameStartsWithIgnoreCase(productName);
    }

    private void updateProduct(Product product, Set<Manufacturer> manufacturerSet) {

        product.removeManufacturers();
        manufacturerSet.forEach(product::addManufacturer);
    }

    @Override
    @Transactional
    public UpdateProductResponse updateProduct(UpdateProductCommand command) {

        return repository.findById(command.getId()).map(product -> {
            Product updatedHoney = updateFields(command, product);
//                    repository.save(updatedHoney); Hibernate changes the entity because of @Transactional
            return UpdateProductResponse.SUCCESS;
        }).orElseGet(() -> new UpdateProductResponse(false, Collections.singletonList("Honey not found with id: " + command.getId())));
    }

    private Product updateFields(UpdateProductCommand command, Product honey) {

        if (command.getName() != null) {
            honey.setProductName(command.getName());
        } return honey;
    }

    @Override
    @Transactional
    public Product addProduct(CreateProductCommand command) {

        Product honey = toProduct(command);
        return repository.save(honey);
    }

    @Override
    public void removeById(Long id) {

        repository.deleteById(id);
    }

    private Product toProduct(CreateProductCommand command) {

        Product honey = new Product(command.getName());
        return honey;
    }
}
