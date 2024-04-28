package ru.inno.task5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tpp_ref_product_register_type")
public class Tpp_ref_product_register_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internal_id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "type", referencedColumnName = "value")
    List<Tpp_product_register> tppProductRegisterList = new ArrayList<>();

    private String value;
    private String register_type_name;
    @ManyToOne
    @JoinColumn(name = "product_class_code", referencedColumnName = "value")
    private Tpp_ref_product_class product_class_code;

    private LocalDateTime register_type_start_date;
    private LocalDateTime register_type_end_date;

    @ManyToOne
    @JoinColumn(name = "account_type", referencedColumnName = "value")
    private Tpp_ref_account_type account_type;
}