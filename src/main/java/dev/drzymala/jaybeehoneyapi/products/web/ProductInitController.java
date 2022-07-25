package dev.drzymala.jaybeehoneyapi.products.web;

import dev.drzymala.jaybeehoneyapi.products.application.port.ProductInitUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/init")
@AllArgsConstructor
public class ProductInitController {

    private final ProductInitUseCase initializer;

    @PostMapping
    @Transactional
    public void initialize() {
        initializer.initialize();
    }

}
