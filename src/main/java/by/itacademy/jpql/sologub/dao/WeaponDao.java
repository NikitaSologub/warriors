package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Weapon;

import java.util.List;

public interface WeaponDao {
    List<Weapon> getAll();

    Weapon get(int id);

    Weapon get(String serialNumber);

    boolean input(Weapon weapon);

    boolean change(Weapon weapon);

    boolean remove(Weapon weapon);
}