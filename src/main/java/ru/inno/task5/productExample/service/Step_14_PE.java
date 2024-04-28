package ru.inno.task5.productExample.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inno.task5.Interface.StepPExampleExecable;
import ru.inno.task5.productExample.model.ProdExample;
import ru.inno.task5.repository.Tpp_productRepo;
import ru.inno.task5.repository.Tpp_product_registerRepo;
import ru.inno.task5.entity.Agreement;
import ru.inno.task5.entity.Tpp_product;
import ru.inno.task5.entity.Tpp_product_register;
import ru.inno.task5.service.CreateTppProduct;
import ru.inno.task5.service.CreateTppProductRegister;

import java.util.List;

@Service
@Data
@Qualifier("14_PE")
public class Step_14_PE implements StepPExampleExecable {
    // ЭП
    @Autowired
    @Qualifier("TppProduct")
    private CreateTppProduct createTppProduct;

    @Autowired
    @Qualifier("TppProductRegister")
    private CreateTppProductRegister createTppProductRegister;

    @Autowired
    private Tpp_productRepo tpp_productRepo;

    @Autowired
    private Tpp_product_registerRepo tpp_product_registerRepo;

    Tpp_product tpp_product;
    List<Agreement> agrList;
    List<Tpp_product_register> tpp_product_registers;

    @Transactional
    public void create_records_tpp(ProdExample modelProdExample) {
        // Создаём запись в Tpp_product
        tpp_product = createTppProduct.create_rec_table(modelProdExample);

        // Добавим записи в таблицу Tpp_product_register
        tpp_product_registers = createTppProductRegister.create_recs_table_product_register(modelProdExample, tpp_product);

        // Добавим записи в таблицу Agreement
        //createAgreement.create_recs_table_agreement(modelProdExample, tpp_product);
        agrList = tpp_product.getAgreementList();
    }

    @Override
    public Object execute(ProdExample prodExample) {
        // Создадим записи в Tpp_product, Tpp_product_register
        System.out.println("Создадим записи в Tpp_product, Tpp_product_register");
        create_records_tpp(prodExample);

        // Сформируем ответ
        System.out.println("Сформируем ответ");
        StructOkAnswer okAnswer = new StructOkAnswer();

        // Заполним ответ Ok
        System.out.println("Заполним ответ Ok");
        okAnswer.setFields(tpp_product.getId(), agrList, tpp_product_registers);

        System.out.println("Step_14_PE");

        return okAnswer;
    }
}