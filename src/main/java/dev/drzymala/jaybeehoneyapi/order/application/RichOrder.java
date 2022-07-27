package dev.drzymala.jaybeehoneyapi.order.application;

import dev.drzymala.jaybeehoneyapi.order.domain.OrderItem;
import dev.drzymala.jaybeehoneyapi.order.domain.OrderStatus;
import dev.drzymala.jaybeehoneyapi.order.domain.Recipient;
import dev.drzymala.jaybeehoneyapi.order.price.OrderPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class RichOrder {

    Long id;

    OrderStatus status;

    Set<OrderItem> items;

    Recipient recipient;

    LocalDateTime createdAt;

    OrderPrice orderPrice;

    BigDecimal finalPrice;
}
