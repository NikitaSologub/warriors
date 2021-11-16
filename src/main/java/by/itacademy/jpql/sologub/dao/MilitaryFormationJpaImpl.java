package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Car;
import by.itacademy.jpql.sologub.model.MilitaryFormation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class MilitaryFormationJpaImpl extends AbstractDaoJpa<MilitaryFormation> implements MilitaryFormationDao {
    private static MilitaryFormationJpaImpl instance;

    protected MilitaryFormationJpaImpl() {
        super(MilitaryFormation.class);
    }

    public static MilitaryFormationJpaImpl getInstance() {
        if (instance == null) {
            synchronized (MilitaryFormationJpaImpl.class) {
                if (instance == null) {
                    instance = new MilitaryFormationJpaImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<MilitaryFormation> getAll() {
        return super.getAll();
    }

    @Override
    public MilitaryFormation get(int id) {
        return super.get(id);
    }

    @Override
    public MilitaryFormation get(String title) {
        MilitaryFormation result = null;
        if (title == null) return null;
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            @SuppressWarnings("JpaQlInspection")
            TypedQuery<MilitaryFormation> typedQuery = manager.createQuery(
                    "select f from MilitaryFormation f where title=?1", MilitaryFormation.class);
            typedQuery.setParameter(1, title);
            result = typedQuery.getSingleResult();
//            System.err.println("Достали обьект MilitaryFormation из бд" + result);
            transaction.commit();
        } catch (PersistenceException e) {
//            System.err.println("Не удалось достать информацию по запросу");
        } finally {
            manager.close();
        }
        return result;
    }

    @Override
    public boolean input(MilitaryFormation formation) {
        return super.input(formation);
    }

    @Override
    public boolean change(MilitaryFormation formation) {
        return super.change(formation);
    }

    @Override
    public boolean remove(MilitaryFormation formation) {
        return super.remove(formation);
    }
}