package ru.inno.task5.repository;

import org.springframework.data.repository.CrudRepository;
import ru.inno.task5.entity.Tpp_ref_account_type;

public interface Tpp_ref_account_typeRepo extends CrudRepository<Tpp_ref_account_type, Long> {
 public  Tpp_ref_account_type findByValue(String value);
}
