package de.alt.shop.repositories;

import java.util.List;

public interface CrudRepository<T> {

    T findById();

    List<T> findAll();


    void save(T model);
    void deleteById(Long id);

    void update(T model);

}
