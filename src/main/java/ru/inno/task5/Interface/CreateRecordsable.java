package ru.inno.task5.Interface;

import java.util.List;

public interface CreateRecordsable {
    // Добавляем одну запись в таблицу
    <T, K> T create_rec_table(K model);

    // Добавляем несколько  записей в таблицу
    <T, K> List<T> create_recs_table(K model);
}
