package by.itacademy.jpql.sologub;

import by.itacademy.jpql.sologub.dao.CatDao;
import by.itacademy.jpql.sologub.dao.CatDaoJpaImpl;
import by.itacademy.jpql.sologub.exception.CatException;
import by.itacademy.jpql.sologub.model.Cat;

import java.util.List;

public class Main {
    public static void main(String[] args) throws CatException {
//        catsExample();


//                          Пример по добавлению обьекта Feeder в бд
//        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//        Feeder f = new Feeder()
//                .withName("Алёшка")
//                .withFood("черемша")
//                .withCar(new Car()
//                        .withModel("Bugatti")
//                        .withSpeed(135));
////        f.getCar().setOwner(f);
//        manager.persist(f);
//        transaction.commit();
//        manager.close();

//                          Пример по извлечению обьектов Feeder из бд
//        for (int id = 1; id < 6; id++) {
//            EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
//            EntityTransaction transaction = manager.getTransaction();
//            transaction.begin();
//            System.out.println("Кормитель " + manager.find(Feeder.class, id));
//            transaction.commit();
//            manager.close();
//        }

//                          Пример по извлечению обьектов Car из бд
//        for (int id = 1; id < 6; id++) {
//            EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
//            EntityTransaction transaction = manager.getTransaction();
//            transaction.begin();
//            System.out.println("Машина " + manager.find(Car.class, id));
//            transaction.commit();
//            manager.close();
//        }
    }

    private static void catsExample() throws CatException {
        // Пример простого одиночного bean Cat (отображение через cat.hbn.xml)
        CatDao catDao = CatDaoJpaImpl.getInstance();

        List<Cat> cats = catDao.getAll();//Пример по извлечению обьектов Cat из бд
        cats.forEach(c -> System.out.println("Котик " + c));

        catDao.input(new Cat()//Пример по добавлению нового Cat в бд
                .withName("Бомба")
                .withColor("Белый")
                .withAge(9));
        cats = catDao.getAll();
        cats.forEach(c -> System.out.println("Котик " + c));

        Cat experimental = catDao.get(1);//Пример по изменению Cat
        System.out.println("Котик до изменения " + experimental);
        experimental.setColor("Полосатый");
        catDao.change(experimental);
        System.out.println("Котик после изменения" + catDao.get(1));

        Cat toBeDeletedCat = catDao.get(2);//Пример по удалению Cat
        System.out.println("Котик до удаления " + toBeDeletedCat);
        catDao.remove(toBeDeletedCat);
        System.out.println("Котик после удаления " + catDao.get(2));

        Cat pumba = catDao.get("пумба");//Пример по доставанию Cat по имени
        System.out.println("Котик по имени пумба " + pumba);
    }
}