package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Warrior;

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
        return getByNamedQueryStringArgument("getByLastname",lastname);
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