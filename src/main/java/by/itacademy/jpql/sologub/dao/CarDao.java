package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAll();

    Car get(int id);

    Car get(String firm);

    boolean input(Car car);

    boolean change(Car car);

    boolean remove(Car car);
}