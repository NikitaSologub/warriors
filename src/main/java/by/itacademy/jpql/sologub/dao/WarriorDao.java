package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Warrior;

import java.util.List;

public interface WarriorDao {
    List<Warrior> getAll();

    Warrior get(int id);

    Warrior get(String lastname);

    boolean input(Warrior warrior);

    boolean change(Warrior warrior);

    boolean remove(Warrior warrior);
}