package ru.inno.task5.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "account_pool_id")
    private Account_pool account_pool_id;

    private String account_number;
    private Boolean bussy;
}
