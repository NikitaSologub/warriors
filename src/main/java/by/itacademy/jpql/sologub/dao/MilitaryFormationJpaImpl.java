package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.MilitaryFormation;

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
        return getByNamedQueryStringArgument("getByTitle",title);
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