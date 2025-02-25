package com.Shop28.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "size")
public class Size extends AbstractEntity<Integer> {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    private Set<ProductDetail> productDetails = new HashSet<>();
}
