package ru.inno.task5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Tpp_product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<Agreement> agreementList = new ArrayList<>();

    private Long product_code_id;
    private Long client_id;
    private String type;
    private String number;
    private Long priority;
    private LocalDateTime date_of_conclusion;
    private LocalDateTime start_date_time;
    private LocalDateTime end_date_time;
    private Long days;
    private Double penalty_rate;
    private Double  nso;
    private Double  threshold_amount;
    private String requisite_type;
    private String interest_rate_type;
    private Double tax_rate;
    private String reasone_close;
    private String state;
}