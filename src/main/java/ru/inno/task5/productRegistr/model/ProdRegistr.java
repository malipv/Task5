package ru.inno.task5.productRegistr.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude

public class ProdRegistr {
    private Long instanceId;            // Id продукта
    private String registryTypeCode;    // Тип регистра
    private String accountType;         // Тип счета
    private String currencyCode;        // Код валюты
    private String branchCode;          // Код филиала
    private String priorityCode;        // Код срочности
    private String mdmCode;             // Id Клиента
    private String clientCode;          // Код клиента
    private String trainRegion;         // Регион ЖД
    private String counter;             // Счетчик
    private String salesCode;           // Код точки продаж
}