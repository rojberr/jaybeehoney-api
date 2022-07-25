package dev.drzymala.jaybeehoneyapi.products.web;

import dev.drzymala.jaybeehoneyapi.products.application.port.ProductUseCase;
import dev.drzymala.jaybeehoneyapi.products.application.port.ProductUseCase.CreateProductCommand;
import dev.drzymala.jaybeehoneyapi.products.application.port.ProductUseCase.UpdateProductCommand;
import dev.drzymala.jaybeehoneyapi.products.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping(value = "/product")
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductUseCase products;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam Optional<String> productName) {

        if (productName.isPresent()) {
            return products.findByProductName(productName.get());
        }
        return products.findAll().stream().limit(5).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        // Example - Send 42 to show how to throw exception inside a method
        if (id.equals(42L)) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "I'm a teapot. Sorry");
        }
        return products
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> addProduct(@Valid @RequestBody RestProductCommand command) {
        Product product = products.addProduct(command.toCreateCommand());
        return ResponseEntity.ok(product.getId());
    }

    @Data
    private static class RestProductCommand {
        @NotBlank
        private String name;

        CreateProductCommand toCreateCommand() {
            return new CreateProductCommand(name);
        }

        UpdateProductCommand toUpdateCommand(Long id) {
            return new UpdateProductCommand(id, name);
        }
    }
}
