package ru.inno.task5.repository;

import org.springframework.data.repository.CrudRepository;
import ru.inno.task5.entity.Tpp_ref_product_class;

import java.util.List;

public interface Tpp_ref_product_classRepo extends CrudRepository<Tpp_ref_product_class, Long> {
 public List<Tpp_ref_product_class> findByValue(String value);
}
