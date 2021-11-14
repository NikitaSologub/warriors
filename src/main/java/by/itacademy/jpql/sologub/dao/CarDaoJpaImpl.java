package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.exception.CarException;
import by.itacademy.jpql.sologub.exception.CatException;
import by.itacademy.jpql.sologub.model.Car;
import by.itacademy.jpql.sologub.model.Cat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CarDaoJpaImpl implements CarDao {
    private static CarDao instance;

    private CarDaoJpaImpl() {
        //singleton
    }

    public static CarDao getInstance() {
        if (instance == null) {
            synchronized (CatDaoJpaImpl.class) {
                if (instance == null) {
                    instance = new CarDaoJpaImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Car> getAll() {
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        List<Car> cars;
        try {
            @SuppressWarnings("JpaQlInspection")
            TypedQuery<Car> typedQuery = manager.createQuery("from Car", Car.class);
            cars = typedQuery.getResultList();
            System.err.println("List<Car> извлечён из бд " + cars);
            transaction.commit();
        } catch (NoResultException e) {
            System.err.println("Не удалось достать List<Car> из бд");
            cars = new ArrayList<>();
        } finally {
            manager.close();
        }
        return cars;
    }

    @Override
    public Car get(int id) throws CarException {
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Car result;
        try {
            transaction.begin();
            result = manager.find(Car.class, id);
//            System.err.println("Достали обьект Car из бд");
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не удалось выполнить операцию доставания Car из бд");
            throw new CarException(e);
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public Car get(String firm) throws CarException {
        Car result = null;
        if (firm == null) return null;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("JpaQlInspection")
            TypedQuery<Car> typedQuery = manager.createQuery("select c from Car c where firm=?1", Car.class);
            typedQuery.setParameter(1, firm);
            result = typedQuery.getSingleResult();
//            System.err.println("Достали обьект Car из бд" + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не удалось достать информацию по запросу");
            throw new CarException(e);
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public boolean input(Car car) {
        if (car == null) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.persist(car);
            result = manager.contains(car);
//            System.err.println("Результат добавления " + cat + " = " + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не получилось добавить обьект. Он имеет поля, которые уже есть у объекта в БД");
            transaction.rollback();
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public boolean change(Car car) {
        if (car == null || car.getId() == 0) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.merge(car);
            result = manager.contains(car);
//            System.err.println("Результат изменения " + car + " = " + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не получилось изменить обьект. Он имеет поля, которые уже есть у объекта в БД");
            transaction.rollback();
            manager.close();
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public boolean remove(Car car) {
        if (car == null || car.getId() == 0) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.remove(car);
            result = manager.contains(car);
//            System.err.println("Результат удаления " + car + " = " + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не получилось удалить обьект в БД");
            transaction.rollback();
            manager.close();
        } finally {
            manager.close();
        }
        return !result;
    }
}