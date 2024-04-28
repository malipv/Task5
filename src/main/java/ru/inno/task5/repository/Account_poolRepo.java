package ru.inno.task5.repository;

import org.springframework.data.repository.CrudRepository;
import ru.inno.task5.entity.Account_pool;

public interface Account_poolRepo extends CrudRepository<Account_pool, Long> {
    Account_pool findFirstByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(String branchCode
                                                                                                    , String CurrencyCode
                                                                                                    , String MdmCode
                                                                                                    , String PriorityCode
                                                                                                    , String registryTypeCode);
}
