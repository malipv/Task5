package ru.inno.task5.productRegistr.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.inno.task5.exceptions.BadReqException;
import ru.inno.task5.Interface.StepPRegisterExecable;
import ru.inno.task5.productRegistr.model.ProdRegistr;

@Service
@Qualifier("1_PR")
public class Step_1_PR implements StepPRegisterExecable {

    @Override
    public void execute(ProdRegistr modelProduct) {
        // Шаг 1 Проверка на обязательность
        System.out.println("Шаг 1 Проверка на обязательность");
        if (modelProduct.getInstanceId() == null) {
            System.out.println(" Ошибка! Идентификатор экземпляра продукта <InstanceId>  не заполнен");
            throw new BadReqException("Идентификатор экземпляра продукта <InstanceId>  не заполнен");
        }
        System.out.println(" STEP_1_PR -> Good");
    }
}