package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Car;
import by.itacademy.jpql.sologub.model.Weapon;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class WeaponDaoJpaImpl extends AbstractDaoJpa<Weapon> implements WeaponDao{
    private static WeaponDaoJpaImpl instance;

    private WeaponDaoJpaImpl() {
        super(Weapon.class);
    }

    public static WeaponDaoJpaImpl getInstance() {
        if (instance == null) {
            synchronized (WeaponDaoJpaImpl.class) {
                if (instance == null) {
                    instance = new WeaponDaoJpaImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Weapon> getAll() {
        return super.getAll();
    }

    @Override
    public Weapon get(int id) {
        return super.get(id);
    }

    @Override
    public Weapon get(String serialNumber) {
        Weapon result = null;
        if (serialNumber == null) return null;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("JpaQlInspection")
            TypedQuery<Weapon> typedQuery = manager.createQuery("select c from Weapon c where serialNumber=?1", Weapon.class);
            typedQuery.setParameter(1, serialNumber);
            result = typedQuery.getSingleResult();
//            System.err.println("Достали обьект Weapon из бд" + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не удалось достать информацию по запросу");
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public boolean input(Weapon weapon) {
        return super.input(weapon);
    }

    @Override
    public boolean change(Weapon weapon) {
        return super.change(weapon);
    }

    @Override
    public boolean remove(Weapon weapon) {
        return super.remove(weapon);
    }
}