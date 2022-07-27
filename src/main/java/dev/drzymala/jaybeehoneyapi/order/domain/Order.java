package dev.drzymala.jaybeehoneyapi.order.domain;

import dev.drzymala.jaybeehoneyapi.commons.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Singular
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Set<OrderItem> items;

}
