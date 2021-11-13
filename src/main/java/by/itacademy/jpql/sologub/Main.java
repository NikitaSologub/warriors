package by.itacademy.jpql.sologub;

import by.itacademy.jpql.sologub.dao.EntityManagerHelper;
import by.itacademy.jpql.sologub.model.Cat;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {
    public static void main(String[] args) {
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        Cat someCat = manager.find(Cat.class, 1);
        System.out.println("Котик " + someCat);
        transaction.commit();
        manager.close();
    }
}