package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Warrior;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class WarriorDaoJpaImpl extends AbstractDaoJpa<Warrior> implements WarriorDao {
    private static WarriorDaoJpaImpl instance;

    private WarriorDaoJpaImpl() {
        super(Warrior.class);
    }

    public static WarriorDaoJpaImpl getInstance() {
        if (instance == null) {
            synchronized (WarriorDaoJpaImpl.class) {
                if (instance == null) {
                    instance = new WarriorDaoJpaImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Warrior> getAll() {
        return super.getAll();
    }

    @Override
    public Warrior get(int id) {
        return super.get(id);
    }

    @Override
    public Warrior get(String lastname) {
        Warrior result = null;
        if (lastname == null) return null;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("JpaQlInspection")//
            TypedQuery<Warrior> typedQuery = manager.createQuery("SELECT w FROM Warrior w WHERE w.info.lastname=?1", Warrior.class);
            typedQuery.setParameter(1, lastname);
            result = typedQuery.getSingleResult();
//            System.err.println("Достали обьект Warrior из бд" + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не удалось достать информацию по запросу");
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public boolean input(Warrior warrior) {
        return super.input(warrior);
    }

    @Override
    public boolean change(Warrior warrior) {
        return super.change(warrior);
    }

    @Override
    public boolean remove(Warrior warrior) {
        return super.remove(warrior);
    }
}