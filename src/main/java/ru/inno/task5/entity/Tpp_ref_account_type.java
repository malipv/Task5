package ru.inno.task5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tpp_ref_account_type")
public class Tpp_ref_account_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internal_id;

    //@Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_type" , referencedColumnName = "value")
    private List<Tpp_ref_product_register_type> tpp_ref_product_register_types = new ArrayList<>();

    private String value;
}
