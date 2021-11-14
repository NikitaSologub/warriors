package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.exception.CarException;
import by.itacademy.jpql.sologub.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAll();

    Car get(int id) throws CarException;

    Car get(String firm) throws CarException;

    boolean input(Car car);

    boolean change(Car car);

    boolean remove(Car car);
}