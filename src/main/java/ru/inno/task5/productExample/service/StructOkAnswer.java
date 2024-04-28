package ru.inno.task5.productExample.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.inno.task5.entity.Agreement;
import ru.inno.task5.entity.Tpp_product_register;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructOkAnswer {
    String instanseId;
    List<String> registerId = new ArrayList<>();
    List<String> supplementaryAgreementId = new ArrayList<>();

    void setFields(Long tpp_product_id, List<Agreement> agrList, List<Tpp_product_register> tpp_product_registers) {
        this.instanseId = tpp_product_id.toString();

        if (!(tpp_product_registers.isEmpty()))
            this.registerId = tpp_product_registers.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        if (!(agrList.isEmpty()))
            this.supplementaryAgreementId = agrList.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
    }
}