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
import ru.inno.task5.productRegistr.model.ProdRegistr;
import ru.inno.task5.productRegistr.service.Step_1_PR;
import ru.inno.task5.productRegistr.service.Step_2_PR;
import ru.inno.task5.productRegistr.service.Step_3_PR;
import ru.inno.task5.repository.Tpp_product_registerRepo;
import ru.inno.task5.entity.Tpp_product_register;
import ru.inno.task5.service.CreateTppProductRegister;

import static org.junit.Assert.assertThrows;

@SpringBootTest(classes = {Step_2_PR.class, Step_1_PR.class, Tpp_product_registerRepo.class})
@SpringBootApplication(scanBasePackages = "ru.inno.task5")
public class CreateRegister {
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
        modelRegister.setInstanceId(10L);
        modelRegister.setRegistryTypeCode("03.012.002_47533_ComSoLd");
    }

    @Autowired
    Step_2_PR step_2_pr;

    @Autowired
    Step_1_PR step_1_pr;

    @Autowired
    Step_3_PR step_3_pr;

    @Autowired
    CreateTppProductRegister createTppProductRegister;

    ProdRegistr modelRegister = new ProdRegistr();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.name", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    @Description("Проверка 1-го шага ТЗ")
    void Step_1_PRTest() {
        modelRegister.setInstanceId(null);
        assertThrows(BadReqException.class, () -> step_1_pr.execute(modelRegister));
        modelRegister.setInstanceId(10L);
        Assertions.assertDoesNotThrow(() -> step_1_pr.execute(modelRegister));
    }

    @Test
    @Description("Проверка 2-го шага ТЗ")
    void Step_2_PRTest() {
        Assertions.assertDoesNotThrow(() -> step_2_pr.execute(modelRegister));
    }

    @Test
    @Description("Проверка 3-го шага ТЗ проверка наличия записи в tpp_ref_product_register")
    void Step_3_PRTest() {
        Assertions.assertDoesNotThrow(() -> step_3_pr.execute(modelRegister));
        modelRegister.setRegistryTypeCode("03.012.002_47533_ComS");
        assertThrows(NotFoundReqException.class, () -> step_3_pr.execute(modelRegister));
    }

    @Test
    @Description("Тестируем добавление записи в регистр")
    void createTppProductRegisterTest() {
        Tpp_product_register tpp_product_register = createTppProductRegister.create_rec_table(modelRegister);
        Assertions.assertNotNull(tpp_product_register);
        assertThrows(BadReqException.class, () -> step_2_pr.execute(modelRegister));
    }
}