package ru.inno.task5;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.inno.task5.exceptions.BadReqException;
import ru.inno.task5.exceptions.NotFoundReqException;
import ru.inno.task5.productExample.model.InstanceArrangement;
import ru.inno.task5.productExample.model.ProdExample;
import ru.inno.task5.productExample.service.*;
import ru.inno.task5.repository.Tpp_productRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

@SpringBootTest(classes = {Step_1_PE.class, Step_11_PE.class, Tpp_productRepo.class})
@SpringBootApplication(scanBasePackages = "ru.inno.task5")
public class CreateExampleNull {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        modelExample.setInstanceId(null);
        modelExample.setProductType("НСО");
        modelExample.setProductCode("03.012.002");
        modelExample.setRegisterType("03.012.002_47533_ComSoLd");
        modelExample.setMdmCode("15");
        modelExample.setContractNumber("122/FK");
        modelExample.setContractDate(LocalDate.parse("2024-03-29", dateTimeFormatter));
        modelExample.setPriority(Integer.valueOf("00"));
        modelExample.setContractId(11);
        modelExample.setBranchCode("0022");
        modelExample.setIsoCurrencyCode("800");
        modelExample.setUrgencyCode("00");
        List<InstanceArrangement> argLst = new ArrayList<>();

        InstanceArrangement arg = new InstanceArrangement();
        arg.setNumber("124/RT");
        arg.setOpeningDate(LocalDate.parse("2024-03-29", dateTimeFormatter));
        argLst.add(arg);

        arg = new InstanceArrangement();
        arg.setNumber("456/RT");
        arg.setOpeningDate(LocalDate.parse("2024-04-10", dateTimeFormatter));
        argLst.add(arg);

        modelExample.setInstanceArrangement(argLst);
    }

    ProdExample modelExample = new ProdExample();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.name", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    Step_1_PE step_1_pe;

    @Autowired
    Step_11_PE step_11_pe;

    @Autowired
    Step_12_PE step_12_pe;

    @Autowired
    Step_13_PE step_13_pe;

    @Autowired
    Step_14_PE step_14_pe;

    @Test
    @Description("1 Проверка 1-го шага ТЗ ЭП Заполнение обязательных значений")
    void step_1_PETest() {
        modelExample.setProductType(null);
        assertThrows(BadReqException.class, ()->step_1_pe.execute(modelExample));

        modelExample.setContractDate(null);
        assertThrows(BadReqException.class, ()->step_1_pe.execute(modelExample));

        modelExample.setContractDate(LocalDate.parse("2024-03-29", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        modelExample.setProductType("НСО");
        Assertions.assertDoesNotThrow(()->step_1_pe.execute(modelExample));

    }
    @Test
    @Description("2 Проверка 1.1  шага ТЗ ЭП")
        // Проверка наличия записи в таблице tpp_product со значением number (если есть отправляем BadStatus )
    void step_11_PETest(){
        Assertions.assertDoesNotThrow(()->step_11_pe.execute(modelExample));
    }

    @Test
    @Description("3 Проверка 1.2  шага ТЗ ЭП")
        // Проверка записей в таблице agreement со значением  number из  массива InstanceArrangement, если есть отправляем BadStatus
    void step_12_PETest(){
        Assertions.assertDoesNotThrow(()->step_12_pe.execute(modelExample));
    }

    @Test
    @Description("4 Проверка 1.3 шага ТЗ ЭП")
        // Выбираем записи из tpp_ref_pproduct_class (чтобы затем найти все записи из tpp_ref_product_register_type)
        // Если не нашли вернем ответ ()
    void step_13_PETest(){
        modelExample.setProductCode("03.00000.002");
        assertThrows(NotFoundReqException.class, ()->step_13_pe.execute(modelExample));
        modelExample.setProductCode("03.012.002");
        Assertions.assertDoesNotThrow(()->step_13_pe.execute(modelExample));
    }

    @Test
    @Description("5 Проверка создания записей")
    void maker_PExampleTest() {
        StructOkAnswer okAnswer = (StructOkAnswer) step_14_pe.execute(modelExample);
        Assertions.assertNotNull(okAnswer);
        Assertions.assertNotNull(okAnswer);
        Assertions.assertNotNull(okAnswer.getInstanseId());
        assertThrows(BadReqException.class, ()->step_11_pe.execute(modelExample));
    }
}