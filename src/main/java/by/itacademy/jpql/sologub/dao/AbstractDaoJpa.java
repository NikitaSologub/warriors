package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDaoJpa<T extends AbstractEntity> {
    private final Class<T> objClass;

    public AbstractDaoJpa(Class<T> objClass) {
        this.objClass = objClass;
    }

    protected List<T> getAll(TypedQuery<T> getAllQuery) {
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        List<T> objectsList;
        try {
            objectsList = getAllQuery.getResultList();
            System.err.println("List<" + objClass.getSimpleName() + "> извлечён из бд");
            transaction.commit();
        } catch (NoResultException e) {
            System.err.println("Не удалось достать List<" + objClass.getSimpleName() + "> из бд");
            objectsList = new ArrayList<>();
        } finally {
            manager.close();
        }
        return objectsList;
    }

    protected T get(int id) {
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        T result = null;
        try {
            transaction.begin();
            result = manager.find(objClass, id);
//            System.err.println("Достали " + result + " из бд");
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не удалось достать" + objClass.getSimpleName() + " из бд по id=" + id);
        } finally {
            manager.close();
        }
        return result;
    }

    protected boolean input(T obj) {
        if (obj == null) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.persist(obj);
            result = manager.contains(obj);
//            System.err.println("Результат добавления " + obj + " = " + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не получилось добавить " + obj + ". Он имеет поля, которые уже есть у объекта в БД");
            transaction.rollback();
        } finally {
            manager.close();
        }
        return result;
    }

    protected boolean change(T obj) {
        if (obj == null || obj.getId() == 0) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.merge(obj);
            result = manager.contains(obj);
//            System.err.println("Результат изменения " + obj + " = " + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не получилось изменить" + obj + "Он имеет поля, которые уже есть у объекта в БД");
            transaction.rollback();
            manager.close();
        } finally {
            manager.close();
        }
        return result;
    }

    protected boolean remove(T obj) {
        if (obj == null || obj.getId() == 0) return false;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean result = false;
        try {
            transaction.begin();
            manager.remove(obj);
            result = manager.contains(obj);
//            System.err.println("Результат удаления " + obj + " = " + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не получилось удалить " + obj + " в БД");
            transaction.rollback();
            manager.close();
        } finally {
            manager.close();
        }
        return !result;
    }
}