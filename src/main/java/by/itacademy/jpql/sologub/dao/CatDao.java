package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Cat;

public interface CatDao {
    Cat getAll();

    Cat get(int id);

    Cat input(Cat cat);

    Cat change(Cat cat);

    Cat delete(Cat cat);
}