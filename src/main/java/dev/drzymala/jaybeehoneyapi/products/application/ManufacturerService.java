package dev.drzymala.jaybeehoneyapi.products.application;

import dev.drzymala.jaybeehoneyapi.products.application.port.ManufacturerUseCase;
import dev.drzymala.jaybeehoneyapi.products.db.ManufacturerJpaRepository;
import dev.drzymala.jaybeehoneyapi.products.domain.Manufacturer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerService implements ManufacturerUseCase {

    private final ManufacturerJpaRepository manufacturerRepository;

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }
}
