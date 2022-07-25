package dev.drzymala.jaybeehoneyapi.products.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.drzymala.jaybeehoneyapi.commons.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    @Column(unique = true)
    private String productName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable
    @JsonIgnoreProperties("products")
    private Set<Manufacturer> manufacturers = new HashSet<>();

    public Product(String productName) {

        this.productName = productName;
    }
}
