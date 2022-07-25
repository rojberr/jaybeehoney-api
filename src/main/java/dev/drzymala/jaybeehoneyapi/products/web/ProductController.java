package dev.drzymala.jaybeehoneyapi.products.web;

import dev.drzymala.jaybeehoneyapi.products.application.port.ProductUseCase;
import dev.drzymala.jaybeehoneyapi.products.domain.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping(value = "product")
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductUseCase product;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam Optional<String> productName) {

        if (productName.isPresent()) {
            return product.findByProductName(productName.get());
        }
        return product.findAll().stream().limit(5).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        // Example - Send 42 to show how to throw exception inside a method
        if (id.equals(42L)) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "I'm a teapot. Sorry");
        }
        return product
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
