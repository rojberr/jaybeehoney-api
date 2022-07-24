package dev.drzymala.jaybeehoneyapi.orders.db;

import dev.drzymala.jaybeehoneyapi.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
}
