package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarDaoJpaImpl extends BaseCrudRepoJpa<Car> implements CarDao {
    private static CarDao instance;

    private CarDaoJpaImpl() {
        super(Car.class);
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
        return super.getAll();
    }

    @Override
    public Car get(int id) {
        return super.get(id);
    }

    @Override
    public Car get(String firm) {
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
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public boolean input(Car car) {
        return super.input(car);
    }

    @Override
    public boolean change(Car car) {
        return super.change(car);
    }

    @Override
    public boolean remove(Car car) {
        return super.remove(car);
    }
}