package ru.inno.task5.productExample.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

// Массив InstanceArrangement
@Data
public class InstanceArrangement {
    private String GeneralAgreementId;                      //ID доп. Ген. согл.
    private String SupplementaryAgreementId;                //ID доп. согл.
    private String arrangementType;                         //Тип согл.
    private Integer shedulerJobId;                          //Иден. период. учета

    @NotNull
    private String Number;                                  //Номер ДС

    @NotNull
    private LocalDate openingDate;                          //Дата нач. сделки

    private LocalDate closingDate;                          //Дата оконч. сделки
    private LocalDate CancelDate;                           //Дата отзыва сделки
    private Integer validityDuration;                       //Срок действия сделки
    private String cancellationReason;                      //Причина расторжения
    private String Status;                                  //Статус
    private LocalDate interestCalculationDate;              //Начисления нач-ся с
    private Double interestRate;                            //Процент нач/ на ост-к
    private Double coefficient;                             //Коэффициент
    private String coefficientAction;                       //Действ. коэфф-та
    private Double minimumInterestRate;                     //Min по ставке
    private String minimumInterestRateCoefficient;          //Коэфф. по min ставке
    private String minimumInterestRateCoefficientAction;    //Действие клэфф. по min ставке
    private Double maximalInterestRate;                     //max по ставке
    private Double maximalInterestRateCoefficient;          //Коэфф-т по max ставке
    private String maximalInterestRateCoefficientAction;    //Действие коэфф-та по max ставке
}