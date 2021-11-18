package by.itacademy.jpql.sologub.dao;

import by.itacademy.jpql.sologub.model.MilitaryFormation;

import java.util.List;

public interface MilitaryFormationDao {
    List<MilitaryFormation> getAll();

    MilitaryFormation get(int id);

    MilitaryFormation get(String title);

    boolean input(MilitaryFormation formation);

    boolean change(MilitaryFormation formation);

    boolean remove(MilitaryFormation formation);
}