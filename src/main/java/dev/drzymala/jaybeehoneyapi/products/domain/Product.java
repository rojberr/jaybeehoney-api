package dev.drzymala.jaybeehoneyapi.products.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.drzymala.jaybeehoneyapi.commons.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "manufacturers")
public class Product extends BaseEntity {

    @Column(unique = true)
    private String productName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable
    @JsonIgnoreProperties("products")
    private Set<Manufacturer> manufacturers = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Product(String productName) {

        this.productName = productName;
    }

    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
        manufacturer.getProducts().add(this);
    }

    public void removeManufacturers() {
        Product self = this;
        manufacturers.forEach(manufacturer -> manufacturer.getProducts().remove(self));
        manufacturers.clear();
    }
}
