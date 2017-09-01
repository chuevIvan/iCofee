package com.icoffee.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {


    /**
     *  Сохранить объект newInstance в базе данных
     */
    PK create(T newInstance);

    /** Извлечь объект, предварительно сохраненный в базе данных, используя
     *   указанный id в качестве первичного ключа
     */
    T read(PK id);

    /**
     * Сохранить изменения, сделанные в объекте.
     * */
    void update(T transientObject);

    /**
     * Удалить объект из базы данных
     * */
    void delete(T persistentObject);

    /**
     * Получить всё из базы данных
     * Метод применяется для тестового проекта
     * В настоящем проекте таких методов быть не должно
     */
    List<T> getAll();
}
