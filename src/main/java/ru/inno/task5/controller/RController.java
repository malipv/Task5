package ru.inno.task5.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.inno.task5.productExample.model.ProdExample;
import ru.inno.task5.productExample.service.Maker_PExample;
import ru.inno.task5.productRegistr.model.ProdRegistr;
import ru.inno.task5.productRegistr.service.Maker_Register;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RController {
    @Autowired
    Maker_PExample createProdExampl;

    @Autowired
    Maker_Register makerRegister;

    // Продуктовый регистр
    @SneakyThrows
    @PostMapping(value = "corporate-settlement-account/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> corporateSettlementAccount(@RequestBody ProdRegistr modelRegister) {
        System.out.println("Зашли в corporateSettlementAccount");
        makerRegister.setModelRegistr(modelRegister);
        Object obj = makerRegister.execute();
        return new ResponseEntity<>(obj, new HttpHeaders(), HttpStatus.CREATED);
    }

    //Экземпляр продуктов
    @PostMapping("corporate-settlement-instance/create")
    public ResponseEntity<?> corporateSettlementInstance(@RequestBody ProdExample modelProdExample) {
        System.out.println("Зашли в corporateSettlementInstance");
        createProdExampl.setModelProdExample(modelProdExample);
        Object obj = createProdExampl.execute();
        return new ResponseEntity<>(obj, new HttpHeaders(), HttpStatus.CREATED);
    }
}