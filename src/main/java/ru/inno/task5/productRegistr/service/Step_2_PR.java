package ru.inno.task5.productRegistr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.inno.task5.exceptions.BadReqException;
import ru.inno.task5.Interface.StepPRegisterExecable;
import ru.inno.task5.productRegistr.model.ProdRegistr;
import ru.inno.task5.entity.Tpp_product_register;
import ru.inno.task5.repository.Tpp_product_registerRepo;

import java.util.List;

@Service
@Qualifier("2_PR")
public class Step_2_PR implements StepPRegisterExecable {
    @Autowired
    Tpp_product_registerRepo tppProductRegisterRepo;

    private boolean foundRepeat(Long product_id, String type) {
        List<Tpp_product_register> tpp_product_registers = tppProductRegisterRepo.findByproductId(product_id);
        return tpp_product_registers.stream().anyMatch(x -> x.getType().equals(type));
    }

    @Override
    public void execute(ProdRegistr modelProduct) {
        // Шаг 2 Проверка на дубли (таблица tpp_product_register)
        System.out.println("Шаг 2 Проверка на дубли (таблица tpp_product_register");
        if (foundRepeat(modelProduct.getInstanceId(), modelProduct.getRegistryTypeCode())) {
            System.out.println(" Ошибка! Параметр registryTypeCode тип регистра " +
                    modelProduct.getRegistryTypeCode() +
                    " уже существует для ЭП  с ИД " + modelProduct.getInstanceId());
            throw new BadReqException("Параметр registryTypeCode тип регистра " +
                    modelProduct.getRegistryTypeCode() +
                    " уже существует для ЭП  с ИД " + modelProduct.getInstanceId());
        }
        System.out.println(" STEP_2_PR -> Good");
    }
}