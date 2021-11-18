package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.MilitaryFormation;

import java.util.List;

public class MilitaryFormationDaoJpaImpl extends BaseCrudRepoJpa<MilitaryFormation> implements MilitaryFormationDao {
    private static MilitaryFormationDaoJpaImpl instance;

    protected MilitaryFormationDaoJpaImpl() {
        super(MilitaryFormation.class);
    }

    public static MilitaryFormationDaoJpaImpl getInstance() {
        if (instance == null) {
            synchronized (MilitaryFormationDaoJpaImpl.class) {
                if (instance == null) {
                    instance = new MilitaryFormationDaoJpaImpl();
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