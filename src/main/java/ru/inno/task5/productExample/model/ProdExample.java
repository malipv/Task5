package ru.inno.task5.productExample.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// Формат запроса ЭП
@Data
public class ProdExample {
    private Long instanceId;                       // Id продукта
    @NotBlank
    private String productType;                     // Тип ЭП
    @NotBlank
    private String productCode;                     // Код продукта в каталоге прод-в
    @NotBlank
    private String registerType;                    // Тип регистра
    @NotBlank
    private String mdmCode;                         // Код кл-та(mdm)
    @NotBlank
    private String contractNumber;                  // Номер дог-ра
    @NotNull
    private LocalDate contractDate;                 // Дата закл. дог-ра обсл.
    @NotNull
    private Integer priority;                       // Приоритет
    private Double interestRatePenalty;             // Штрафн. проц. ставка
    private Double minimalBalance;                  // Несниж. остаток
    private Double thresholdAmount;                 // Порог. сумма
    private String accountingDetails;               // Реквизиты выплаты
    private String rateType;                        // Выбор ставки
    private Double taxPercentageRate;               // Ставка налог.
    private Double technicalOverdraftLimitAmount;   // Лимит тех/овер.
    @NotNull
    private Integer contractId;                     // Id договора
    @NotBlank
    private String BranchCode;                      // Код филиала
    @NotBlank
    private String IsoCurrencyCode;                 // Код валюты
    @NotBlank
    private String urgencyCode;                     // Код срочн-ти договора
    private Integer ReferenceCode;                  // Код точки продаж
    private Map<String, List<AdditionalPropertiesVip>> additionalPropertiesVips; // массив доп. признаков
    private List<InstanceArrangement> instanceArrangement; // Массив доп. соглашений

    public ProdExample() {
        /*
        instanceId = 11L;
        productType = "НСО";
        productCode = "03.012.002";
        registerType = "03.012.002_47533_ComSoLd";
        mdmCode      = "15";
        contractNumber = "122/FK";
        contractDate = LocalDate.now();
        priority = 1;
        interestRatePenalty = 0.01;
        minimalBalance = 20000.00;
        thresholdAmount = 100000.00;
        accountingDetails = "REKV VYPLATA";
        rateType = "0";
        taxPercentageRate = 2.1;
        technicalOverdraftLimitAmount = 10000.00;
        contractId = 4;
        BranchCode = "0022";
        IsoCurrencyCode = "800";
        urgencyCode = "00";
        ReferenceCode = 11;


        List<ModelAdditionalPropertiesVip>  additionalPropertiesVips1 = new ArrayList<>();
        ModelAdditionalPropertiesVip md1 = new ModelAdditionalPropertiesVip();
        md1.setKey("RailwayRegionOwn");
        md1.setValue("ABC");
        md1.setName("Регион принадлежности железной дороги");
        additionalPropertiesVips1.add(md1);
        ModelAdditionalPropertiesVip md2 = new ModelAdditionalPropertiesVip();
        md2.setKey("counter");
        md2.setValue("123");
        md2.setName("Счетчик");
        additionalPropertiesVips1.add(md2);
        additionalPropertiesVips = new HashMap<>();
        additionalPropertiesVips.put("data", additionalPropertiesVips1);
        instanceArrangement = new ArrayList<>();

        ModelInstanceArrangement mdArg1 = new ModelInstanceArrangement();
        mdArg1.setGeneralAgreementId("11");
        mdArg1.setSupplementaryAgreementId("22");
        mdArg1.setArrangementType("НСО");
        mdArg1.setShedulerJobId(777);
        mdArg1.setNumber("123/RT");
        mdArg1.setOpeningDate(LocalDate.now());
        mdArg1.setClosingDate(LocalDate.now());
        mdArg1.setCancelDate(LocalDate.now());
        mdArg1.setValidityDuration(30);
        mdArg1.setCancellationReason("Reason cancelation");
        mdArg1.setStatus("Открыт");
        mdArg1.setInterestCalculationDate(LocalDate.now());
        mdArg1.setInterestRate(2.5);
        mdArg1.setCoefficient(1.25);
        mdArg1.setCoefficientAction("Aplly before 01.01.2025");
        mdArg1.setMinimumInterestRate(0.01);
        mdArg1.setMinimumInterestRateCoefficient("0.05");
        mdArg1.setMinimumInterestRateCoefficientAction("Action by Min Rate Coeff");
        mdArg1.setMaximalInterestRate(0.05);
        mdArg1.setMaximalInterestRateCoefficient(1.20);
        mdArg1.setMaximalInterestRateCoefficientAction("Action by Max Rate Coeff");
        instanceArrangement.add(mdArg1);

        ModelInstanceArrangement mdArg2 = new ModelInstanceArrangement();
        mdArg2.setGeneralAgreementId("33");
        mdArg2.setSupplementaryAgreementId("44");
        mdArg2.setArrangementType("СМО");
        mdArg2.setShedulerJobId(888);
        mdArg2.setNumber("456/RT");
        mdArg2.setOpeningDate(LocalDate.now());
        mdArg2.setClosingDate(LocalDate.now());
        mdArg2.setCancelDate(LocalDate.now());
        mdArg2.setValidityDuration(40);
        mdArg2.setCancellationReason("Reason cancelation");
        mdArg2.setStatus("Открыт");
        mdArg2.setInterestCalculationDate(LocalDate.now());
        mdArg2.setInterestRate(3.5);
        mdArg2.setCoefficient(2.25);
        mdArg2.setCoefficientAction("Aplly before 01.09.2025");
        mdArg2.setMinimumInterestRate(0.15);
        mdArg2.setMinimumInterestRateCoefficient("0.08");
        mdArg2.setMinimumInterestRateCoefficientAction("Action by Min Rate Coeff");
        mdArg2.setMaximalInterestRate(0.09);
        mdArg2.setMaximalInterestRateCoefficient(1.40);
        mdArg2.setMaximalInterestRateCoefficientAction("Action by Max Rate Coeff");
        instanceArrangement.add(mdArg2);
        */
    }
}