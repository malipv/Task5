package ru.inno.task5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Tpp_ref_product_class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internal_id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_class_code", referencedColumnName = "value")
    private List<Tpp_ref_product_register_type> tpp_ref_product_register_types = new ArrayList<>();

    private String value;

    private String gbi_code;
    private String gbi_name;
    private String product_row_code;
    private String product_row_name;
    private String subclass_code;
    private String subclass_name;
}
