package ru.inno.task5.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.inno.task5.Interface.CreateRecordsable;
import ru.inno.task5.productExample.model.ProdExample;
import ru.inno.task5.repository.Tpp_productRepo;
import ru.inno.task5.repository.Tpp_ref_product_classRepo;
import ru.inno.task5.entity.Tpp_product;
import ru.inno.task5.entity.Tpp_ref_product_class;

import java.util.List;

@Data
@Service
@Qualifier("TppProduct") //  Если понадобится определить несколько реализаций одного интерфейса
public class CreateTppProduct implements CreateRecordsable {
    @Autowired
    Tpp_productRepo tpp_productRepo;

    @Autowired
    Tpp_ref_product_classRepo tpp_ref_product_classRepo;

    @Override
    public <T, K> T create_rec_table(K model) {
        ProdExample prodExample = (ProdExample) model;
        Tpp_product tpp_product = new Tpp_product();
        List<Tpp_ref_product_class> tppRefProductClassLst = tpp_ref_product_classRepo.findByValue(prodExample.getProductCode());

        if (!(tppRefProductClassLst == null))
            tpp_product.setProduct_code_id(tppRefProductClassLst.get(0).getInternal_id());

        tpp_product.setClient_id(Long.valueOf(prodExample.getMdmCode()));
        tpp_product.setNumber(prodExample.getContractNumber());
        tpp_product.setPriority(Long.valueOf(prodExample.getPriority()));
        tpp_product.setType(prodExample.getProductType());
        tpp_product.setDate_of_conclusion(prodExample.getContractDate().atStartOfDay());

        tpp_product.setPenalty_rate(prodExample.getInterestRatePenalty());
        tpp_product.setThreshold_amount(prodExample.getThresholdAmount());
        tpp_product.setTax_rate(prodExample.getTaxPercentageRate());
        Tpp_product tpp_productSave = tpp_productRepo.save(tpp_product);

        System.out.println("Добавлена запись в Tpp_product");

        return (T) tpp_productSave;
    }

    @Override
    public <T, K> List<T> create_recs_table(K model) {
        // возможна реализация в будущем
        return null;
    }
}