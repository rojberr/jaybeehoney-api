package dev.drzymala.jaybeehoneyapi.products.application;

import dev.drzymala.jaybeehoneyapi.products.application.port.ProductInitUseCase;

import javax.transaction.Transactional;

public class ProductInitializerService implements ProductInitUseCase {

    @Override
    @Transactional
    public void initialize() {
        initData();
    }

    private void initData() {

    }
}
