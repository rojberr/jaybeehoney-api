package dev.drzymala.jaybeehoneyapi.products.application.port;

import dev.drzymala.jaybeehoneyapi.products.domain.Manufacturer;

import java.util.List;

public interface ManufacturerUseCase {

    List<Manufacturer> findAll();
}
