package ru.inno.task5.repository;

import org.springframework.data.repository.CrudRepository;
import ru.inno.task5.entity.Tpp_ref_product_register_type;

import java.util.List;

public interface Tpp_ref_product_register_typeRepo extends CrudRepository<Tpp_ref_product_register_type, Long> {
    List<Tpp_ref_product_register_type> findByValue(String value);
}
