package dev.drzymala.jaybeehoneyapi.products.db;

import dev.drzymala.jaybeehoneyapi.products.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long> {

    Optional<Manufacturer> findByFirstNameIgnoreCase(String firsName);
}
