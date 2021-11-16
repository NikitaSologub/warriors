package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Cat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class CatDaoJpaImpl extends AbstractDaoJpa<Cat> implements CatDao {
    private static CatDao instance;

    private CatDaoJpaImpl() {
        super(Cat.class);
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
        return super.getAll();
    }

    @Override
    public Cat get(int id) {
        return super.get(id);
    }

    @Override
    public Cat get(String name) {
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
        return super.input(cat);
    }

    @Override
    public boolean change(Cat cat) {
        return super.change(cat);
    }

    @Override
    public boolean remove(Cat cat) {
        return super.remove(cat);
    }
}