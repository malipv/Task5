package ru.inno.task5.repository;

import org.springframework.data.repository.CrudRepository;
import ru.inno.task5.entity.Account;

public interface AccountRepo extends CrudRepository<Account, Long> {
}
