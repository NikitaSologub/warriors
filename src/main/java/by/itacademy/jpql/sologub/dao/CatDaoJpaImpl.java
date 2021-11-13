package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Cat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CatDaoJpaImpl implements CatDao {
    private static CatDao instance;

    private CatDaoJpaImpl() {
        //singleton
    }

    public static CatDao getInstance() {
        if (instance == null) {
            synchronized (CatDaoJpaImpl.class) {
                if (instance == null) {
                    instance = new CatDaoJpaImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Cat getAll() {
        return null;
    }

    @Override
    public Cat get(int id) {
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Cat result = manager.find(Cat.class, id);

        transaction.commit();
        manager.close();
        return result;
    }

    @Override
    public Cat input(Cat cat) {
        return null;
    }

    @Override
    public Cat change(Cat cat) {
        return null;
    }

    @Override
    public Cat delete(Cat cat) {
        return null;
    }
}