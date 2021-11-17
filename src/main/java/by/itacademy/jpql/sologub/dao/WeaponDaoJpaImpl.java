package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.Weapon;

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
        return getByNamedQueryStringArgument("getBySerialNumber", serialNumber);
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