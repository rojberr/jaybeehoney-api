package dev.drzymala.jaybeehoneyapi.products.web;

import dev.drzymala.jaybeehoneyapi.products.application.port.ProductUseCase;
import dev.drzymala.jaybeehoneyapi.products.domain.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
