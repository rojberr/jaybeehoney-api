package dev.drzymala.jaybeehoneyapi.products.domain;

import dev.drzymala.jaybeehoneyapi.commons.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    @Column(unique = true)
    private String productName;
}
