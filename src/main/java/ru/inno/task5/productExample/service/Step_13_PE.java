package ru.inno.task5.productExample.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.inno.task5.Interface.StepPExampleExecable;
import ru.inno.task5.exceptions.NotFoundReqException;
import ru.inno.task5.productExample.model.ProdExample;
import ru.inno.task5.repository.Tpp_ref_product_classRepo;
import ru.inno.task5.entity.Tpp_ref_product_register_type;
import ru.inno.task5.service.CreateTppProductRegister;
import java.util.List;

// Найти связанные записи( если не нашли отправить статус NOT_FOUND Шаг 1.3 ТЗ)
@Data
@Service
@Qualifier("13_PE")
public class Step_13_PE implements StepPExampleExecable {
    @Autowired
    Tpp_ref_product_classRepo tpp_ref_product_classRepo;

    @Autowired
    CreateTppProductRegister createTppProductRegister;

    @Override
    public ResponseEntity<?> execute(ProdExample prodExample) {
        // Выбираем записи из tpp_ref_pproduct_class (чтобы затем найти все записи из  tpp_ref_product_register_type)
        System.out.println("Выбираем записи из tpp_ref_pproduct_class (чтобы затем найти все записи из  tpp_ref_product_register_type)");
        List<Tpp_ref_product_register_type>   tppTypeLst =  createTppProductRegister.getLstType(prodExample.getProductCode());

        // Если не нашли вернем ответ
        if (tppTypeLst.isEmpty()) {
            System.out.println("Код продукта  " + prodExample.getProductCode() +
                    " не найден в каталоге продуктов tpp_ref_product_class");
            throw new NotFoundReqException("Код продукта  " + prodExample.getProductCode() +
                    " не найден в каталоге продуктов tpp_ref_product_class");
        }

        System.out.println("Step_13_PE"); // Оставлено Отладка

        // Если все поля заполнены позволяем выполнять функционал далее
        return null;
    }
}