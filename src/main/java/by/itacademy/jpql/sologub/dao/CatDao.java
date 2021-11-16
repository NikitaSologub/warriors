package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Cat;

import java.util.List;

public interface CatDao {
    List<Cat> getAll();

    Cat get(int id);

    Cat get(String name);

    boolean input(Cat cat);

    boolean change(Cat cat);

    boolean remove(Cat cat);
}