package dev.drzymala.jaybeehoneyapi.order.price;

import java.math.BigDecimal;

public class OrderPrice {

    BigDecimal itemsPrice;

    BigDecimal deliveryPrice;

    BigDecimal discounts;

    public BigDecimal finalPrice() {
        return itemsPrice.add(deliveryPrice).subtract(discounts);
    }
}
