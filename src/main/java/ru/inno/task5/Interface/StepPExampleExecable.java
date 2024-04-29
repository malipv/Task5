package ru.inno.task5.Interface;

import org.springframework.http.ResponseEntity;
import ru.inno.task5.productExample.model.ProdExample;

// Выполнение шагов обработки Экземпляр Продукта
public interface StepPExampleExecable {
    Object execute(ProdExample prodExample);
}