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
import ru.inno.task5.service.CreateAgreement;
import java.util.List;

@Data
@Service
@Qualifier("23_PE")
public class Step_23_PE implements StepPExampleExecable {
    @Autowired
    @Qualifier("Agreement")
    private CreateAgreement createAgreement;

    @Autowired
    private Tpp_product_registerRepo tpp_product_registerRepo;

    @Autowired
    private Tpp_productRepo tpp_productRepo;

    private Tpp_product tppProduct;
    private List<Agreement> tppAgrLst;
    private List<Tpp_product_register> tppRegLst;

    @Transactional
    public  void create_records_agreement(ProdExample modelProdExample){
        tppProduct = tpp_productRepo.findById(modelProdExample.getInstanceId()).orElse(null);

        // Добавим записи в таблицу Agreement
        tppAgrLst= createAgreement.create_recs_table_agreement(modelProdExample, tppProduct);

        // Будем выводить все agreement по product_id
        tppAgrLst = createAgreement.findAllAgreement(tppProduct);

        // Найлем записи в реестре платежей для формирования ответа
        tppRegLst = tpp_product_registerRepo.findByproductId(tppProduct.getId());
    }

    @Override
    public Object execute(ProdExample prodExample) {
        create_records_agreement(prodExample);

        // Сформируем ответ
        System.out.println("Сформируем ответ");
        StructOkAnswer okAnswer = new StructOkAnswer();

        // Заполним ответ Ok
        System.out.println("Заполним ответ Ok");
        okAnswer.setFields(tppProduct.getId(),tppAgrLst,tppRegLst);

        System.out.println("Step_23_PE");

        return  okAnswer;
    }
}