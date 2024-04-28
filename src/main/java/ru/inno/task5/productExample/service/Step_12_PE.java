package ru.inno.task5.productExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.inno.task5.Interface.StepPExampleExecable;
import ru.inno.task5.exceptions.BadReqException;
import ru.inno.task5.productExample.model.InstanceArrangement;
import ru.inno.task5.productExample.model.ProdExample;
import ru.inno.task5.repository.AgreementRepo;
import ru.inno.task5.entity.Agreement;
import java.util.List;

// Проверка таблицы ДС(agreement) на дубли(Шаг 1.2. ТЗ)
@Service
@Qualifier("12_PE") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_12_PE implements StepPExampleExecable {
    @Autowired
    AgreementRepo agreementRepo;

    @Override
    public ResponseEntity<?> execute(ProdExample prodExample) {
        // Проверка записей в таблице agreement со значением  number из  массива InstanceArrangement, если есть отправляем BadStatus
        System.out.println("Проверка записей в таблице agreement со значением  number из  массива InstanceArrangement, если есть отправляем BadStatus");
        List<InstanceArrangement>  agreeementLst = prodExample.getInstanceArrangement();
        for (InstanceArrangement agr: agreeementLst) {
            Agreement agreement = agreementRepo.findFirstByNumber(agr.getNumber());
            if (!(agreement == null)) {
                System.out.println(" Ошибка. Параметр Дополнительного соглашения(сделки) Number " + agr.getNumber() +
                        " уже существует для ЭП с ИД " + agreement.getId());
                throw new BadReqException("Параметр Дополнительного соглашения(сделки) Number " + agr.getNumber() +
                        " уже существует для ЭП с ИД " + agreement.getId());
            }
        }
        System.out.println("Step_12_PE"); // Оставлено Отладка

        // Если все поля заполнены позволяем выполнять функционал далее
        return null;
    }
}