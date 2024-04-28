package ru.inno.task5.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.inno.task5.enumState.State;
import ru.inno.task5.Interface.CreateRecordsable;
import ru.inno.task5.productExample.model.ProdExample;
import ru.inno.task5.productRegistr.model.ProdRegistr;
import ru.inno.task5.repository.Account_poolRepo;
import ru.inno.task5.repository.Tpp_product_registerRepo;
import ru.inno.task5.repository.Tpp_ref_product_classRepo;
import ru.inno.task5.entity.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Qualifier("TppProductRegister") // Если понадобится определить несколько реализаций одного интерфейса
public class CreateTppProductRegister implements CreateRecordsable {
    @Autowired
    Account_poolRepo account_poolRepo;

    @Autowired
    Tpp_product_registerRepo tpp_product_registerRepo;

    @Autowired
    Tpp_ref_product_classRepo tpp_ref_product_classRepo;

    private List<Tpp_ref_product_register_type> tppTypeLst = new ArrayList<>();
    private Tpp_product tpp_product;

    // Выбираем связанные записи из tpp_ref_pproduct_class
    public List<Tpp_ref_product_register_type> getLstType(String product_code) {
        List<Tpp_ref_product_register_type> tppTypeList = new ArrayList<>();
        List<Tpp_ref_product_class> tppRefProductClassLst;
        tppRefProductClassLst = tpp_ref_product_classRepo.findByValue(product_code); //
        for (Tpp_ref_product_class tpcl : tppRefProductClassLst) {
            // Находим связанные записи Tpp_ref_product_register_type(нас интересуют все с account_type = "Клиентский")
            List<Tpp_ref_product_register_type> tppProdRegTypeLst = tpcl.getTpp_ref_product_register_types();

            // среди найденных отбираем с Account_type = "Клиентский"
            for (Tpp_ref_product_register_type type : tppProdRegTypeLst) {
                if (type.getAccount_type().getValue().equals("Клиентский"))
                    tppTypeList.add(type);
            }
        }
        tppTypeLst = tppTypeList;
        return tppTypeLst;
    }

    // Добавляем запись в таблицу tpp_product_register
    @Override
    public <T, K> T create_rec_table(K model) {
        ProdRegistr prodRegistr = (ProdRegistr) model;
        Tpp_product_register tpp_product_register = new Tpp_product_register();
        Account_pool account_pool = account_poolRepo.findFirstByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
                prodRegistr.getBranchCode()              // branch_code
                , prodRegistr.getCurrencyCode()          // currency_code
                , prodRegistr.getMdmCode()               // mdm_code
                , prodRegistr.getPriorityCode()          // priority_code
                , prodRegistr.getRegistryTypeCode()      // registry_type_code
        );

        tpp_product_register.setProductId(prodRegistr.getInstanceId());
        tpp_product_register.setType(prodRegistr.getRegistryTypeCode());
        tpp_product_register.setCurrency_code(prodRegistr.getCurrencyCode());
        tpp_product_register.setState(State.OPEN.getName());

        // Номер счета берется первый (заполняем инфо по счетам)
        if (!(account_pool == null)) {

            List<Account> accountLst = account_pool.getAccountList();
            Account account = accountLst.get(0);
            tpp_product_register.setAccount(account.getId());
            tpp_product_register.setAccountNumber(account.getAccount_number());
        }

        Tpp_product_register tpp_product_registerSave = tpp_product_registerRepo.save(tpp_product_register);
        System.out.println("Добавлена запись в Tpp_product_register");
        return (T) tpp_product_registerSave;
    }

    // Добавляем несколько записей в табицу tpp_product_register
    @Override
    public <T, K> List<T> create_recs_table(K model) {
        ProdExample prodExample = (ProdExample) model;
        List<Tpp_product_register> prodRegLst = new ArrayList<>();

        tppTypeLst = getLstType(prodExample.getProductCode());
        for (Tpp_ref_product_register_type typeLst : tppTypeLst) {

            ProdRegistr prodRegistr = new ProdRegistr();
            prodRegistr.setInstanceId(tpp_product.getId());
            prodRegistr.setBranchCode(prodExample.getBranchCode());
            prodRegistr.setCurrencyCode(prodExample.getIsoCurrencyCode());
            prodRegistr.setMdmCode(prodExample.getMdmCode());
            prodRegistr.setPriorityCode(prodExample.getUrgencyCode());
            prodRegistr.setRegistryTypeCode(typeLst.getValue());

            // Добавляем записи
            Tpp_product_register tpp_product_register = create_rec_table(prodRegistr);

            System.out.println("Добавлена запись");
            prodRegLst.add(tpp_product_register);
        }
        return (List<T>) prodRegLst;
    }

    public List<Tpp_product_register> create_recs_table_product_register(ProdExample prodExample
            , Tpp_product tpp_product) {
        this.tpp_product = tpp_product;
        return create_recs_table(prodExample);
    }
}