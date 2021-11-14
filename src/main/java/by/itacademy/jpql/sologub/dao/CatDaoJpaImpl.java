package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.exception.CatException;
import by.itacademy.jpql.sologub.model.Cat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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
    public List<Cat> getAll() {
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        List<Cat> cats;
        try {
            @SuppressWarnings("JpaQlInspection")
            TypedQuery<Cat> typedQuery = manager.createQuery("from Cat", Cat.class);
            cats = typedQuery.getResultList();
//            System.err.println("List<Cat> извлечён из бд " + cats);
            transaction.commit();
        } catch (NoResultException e) {
//            System.err.println("Не удалось достать List<Cat> из бд");
            cats = new ArrayList<>();
        } finally {
            manager.close();
        }
        return cats;
    }

    @Override
    public Cat get(int id) throws CatException {
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Cat result;
        try {
            transaction.begin();
            result = manager.find(Cat.class, id);
//            System.err.println("Достали обьект Cat из бд");
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не удалось выполнить операцию доставания Cat из бд");
            throw new CatException(e);
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public Cat get(String name) throws CatException {
        Cat result = null;
        if (name == null) return null;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("JpaQlInspection")
            TypedQuery<Cat> typedQuery = manager.createQuery("select c from Cat c where name=?1", Cat.class);
            typedQuery.setParameter(1, name);
            result = typedQuery.getSingleResult();
            System.err.println("Достали обьект Cat из бд" + result);
            transaction.commit();
        } catch (NoResultException e) {
            System.err.println("Не удалось достать информацию по запросу");
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public boolean input(Cat cat) {
        if (cat == null) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.persist(cat);
            result = manager.contains(cat);
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
    public boolean change(Cat cat) {
        if (cat == null || cat.getId() == 0) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.merge(cat);
            result = manager.contains(cat);
//            System.err.println("Результат изменения " + cat + " = " + result);
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
    public boolean remove(Cat cat) {
        if (cat == null || cat.getId() == 0) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.remove(cat);
            result = manager.contains(cat);
//            System.err.println("Результат удаления " + cat + " = " + result);
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