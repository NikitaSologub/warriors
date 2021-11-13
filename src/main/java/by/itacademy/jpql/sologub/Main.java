package by.itacademy.jpql.sologub;


import by.itacademy.jpql.sologub.dao.CatDao;
import by.itacademy.jpql.sologub.dao.CatDaoJpaImpl;
import by.itacademy.jpql.sologub.dao.EntityManagerHelper;
import by.itacademy.jpql.sologub.model.Car;
import by.itacademy.jpql.sologub.model.Feeder;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        CatDao catDao = CatDaoJpaImpl.getInstance();

        for (int id = 1; id < 5; id++) {
            System.out.println("Котик " + catDao.get(id));
        }

        for (int id = 1; id < 6; id++) {
            EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            transaction.begin();
            System.out.println("Кормитель " + manager.find(Feeder.class, id));
            transaction.commit();
            manager.close();
        }
//
//        for (int id = 1; id < 6; id++) {
//            EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
//            EntityTransaction transaction = manager.getTransaction();
//
//            transaction.begin();
//            System.out.println("Машина " + manager.find(Car.class, id));
//            transaction.commit();
//            manager.close();
//        }
    }
}