package ru.inno.task5.productExample.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.inno.task5.Interface.StepPExampleExecable;
import ru.inno.task5.productExample.model.ProdExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Создание ЭП
@Data
@Service
public class Maker_PExample {
    // Класс для парсинга JSON
    ProdExample modelProdExample;

    // Если не заполнен InstanceId определяем логику выполнения
    List<StepPExampleExecable> stepLstNullInstance = new ArrayList<>();

    // Если заполнен InstanceId определяем логику выполнения
    List<StepPExampleExecable> stepLstNotNullInstance = new ArrayList<>();

    public Maker_PExample() {
    }

    @Autowired
    public Maker_PExample(@Qualifier("1_PE") Step_1_PE step_1
                        , @Qualifier("11_PE") Step_11_PE step_11
                        , @Qualifier("12_PE") Step_12_PE step_12
                        , @Qualifier("13_PE") Step_13_PE step_13
                        , @Qualifier("14_PE") Step_14_PE step_14
                        , @Qualifier("21_PE") Step_21_PE step_21
                        , @Qualifier("23_PE") Step_23_PE step_23
                        )
    {
        System.out.println("Запуск проверок");
        stepLstNullInstance = List.of(step_1, step_11, step_12, step_13, step_14); // Если НЕ задан InstanceId
        stepLstNotNullInstance = List.of(step_1, step_21, step_12, step_23); // Если Задан InstanceId
        System.out.println("Проверки окончены");
       /*
       // step_1 Проверка Request.Body на обязательность заполнения(Шаг 1 ТЗ) stepLstNullInstance.add(step_1);
       // step_11 Проверка таблицы ЭП(tpp_product) на дубли (Шаг 1.1. ТЗ) stepLstNullInstance.add(step_11);
       // step_12 Проверка таблицы ДС(agreement) на дубли(Шаг 1.2. ТЗ) stepLstNullInstance.add(step_12);
       // step_13 Найти связанные записи( если не нашли отправить статус NOT_FOUND Шаг 1.3 ТЗ stepLstNullInstance.add(step_13);
       // step_14 Добавить строки в tpp_product и в tpp_product_registry(шаг 1.4 и 1.5)(одна транзакция) stepLstNullInstance.add(step_14);
       // step_1 Проверка Request.Body на обязательность заполнения(Шаг 1 ТЗ) stepLstNotNullInstance.add(step_1);
       // step_21 Проверка таблицы ЭП(tpp_product) на наличие записи (шаг 2.1 ТЗ) stepLstNotNullInstance.add(step_21);
       // step_12 Проверка таблицы agreement на дубли stepLstNotNullInstance.add(step_12);
       // step_23 Добавляем записи в таблицу agreement stepLstNotNullInstance.add(step_23);*/
    }

    Object CreateAnswerOk(StructOkAnswer structOkAnswer) {
        Map<String, StructOkAnswer> mp = new HashMap<>();
        mp.put("data", structOkAnswer);
        return mp;
    }

    public Object execute() {
        List<StepPExampleExecable> listExecArr;
        if (modelProdExample.getInstanceId() == null) {
            System.out.println("НЕ задан InstanceId");
            listExecArr = new ArrayList<>(stepLstNullInstance);
        }
        else {
            System.out.println("НЕ задан InstanceId");
            listExecArr = new ArrayList<>(stepLstNotNullInstance);
        }

        System.out.println("Выполняем шаги последовательно");
        Object resp = null;
        for (StepPExampleExecable step : listExecArr) {
            resp = step.execute(modelProdExample);
        }
        // если дошли и не свалились
        if (!(resp == null)) {
            System.out.println("Дошли и не свалились");
            return CreateAnswerOk((StructOkAnswer) resp);
        }

        // Если вдруг ....
        System.out.println("Непредвиденная ошибка чтения/записи данных");
        return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
                .body(Map.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Непредвиденная ошибка чтения/записи данных"));
    }
}