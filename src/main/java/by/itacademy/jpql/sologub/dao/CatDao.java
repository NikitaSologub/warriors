package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.exception.CatException;
import by.itacademy.jpql.sologub.model.Cat;

import java.util.List;

public interface CatDao {
    List<Cat> getAll();

    Cat get(int id) throws CatException;

    Cat get(String name) throws CatException;

    boolean input(Cat cat);

    boolean change(Cat cat);

    boolean remove(Cat cat);
}