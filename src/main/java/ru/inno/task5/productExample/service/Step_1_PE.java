package ru.inno.task5.productExample.service;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.inno.task5.Interface.StepPExampleExecable;
import ru.inno.task5.exceptions.BadReqException;
import ru.inno.task5.productExample.model.InstanceArrangement;
import ru.inno.task5.productExample.model.ProdExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Проверка Request.Body на обязательность заполнения(Шаг 1 ТЗ)
@Service
@Validated
@Qualifier("1_PE")
public class Step_1_PE implements StepPExampleExecable {
    // Накапливаем все ошибочные поля
    private List<String> errLst = new ArrayList<>();

    // выводим в List собранные ошибочные величины
    private <T> void messOut(Set<ConstraintViolation<T>> violations) {
        if (!violations.isEmpty())
            for (ConstraintViolation<T> violation : violations) {
                if (violation.getMessage().contains("blank") || violation.getMessage().contains("null"))
                    errLst.add("Имя обязательного параметра " + violation.getPropertyPath().toString() + " : не зполнено значение ");
            }
    }

    // Проверяем InstanceArrangement
    void validateModel(@Valid InstanceArrangement instanceArrangement, Validator validator) {
        Set<ConstraintViolation<InstanceArrangement>> violations = validator.validate(instanceArrangement);
        messOut(violations);
    }

    // Проверяем  ProdExample
    void validateModel(@Valid ProdExample prodExample, Validator validator) {
        Set<ConstraintViolation<ProdExample>> violations = validator.validate(prodExample);
        messOut(violations);
    }

    public Object execute(ProdExample prodExample) {
        errLst = new ArrayList<>();
        // Шаг 1 Проверка на обязательность заполнения переданных значений
        System.out.println("Шаг 1. Проверка на обязательность заполнения переданных значений");

        // Определяем валидатор
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Валидируем модель данных
        validateModel(prodExample, validator);

        // Повторяем для InstanceAgreement
        List<InstanceArrangement> instArr = prodExample.getInstanceArrangement();
        for (InstanceArrangement instanceArrangement : instArr)
            validateModel(instanceArrangement, validator);

        // Если начитали ошибки формируем BadRequest
        if (!errLst.isEmpty()) {
            System.out.println(" Ошибка. Шаг 1. Проверка на обязательность заполнения переданных значений не пройдена!");
            throw new BadReqException(errLst.toString());
        }
        System.out.println("Step_1_PE"); // Оставлено Отладка

        // Если все поля заполнены позволяем выполнять функционал далее
        return null;
    }
}