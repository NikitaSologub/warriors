package by.itacademy.jpql.sologub;

import by.itacademy.jpql.sologub.dao.CarDao;
import by.itacademy.jpql.sologub.dao.CarDaoJpaImpl;
import by.itacademy.jpql.sologub.dao.CatDao;
import by.itacademy.jpql.sologub.dao.CatDaoJpaImpl;
import by.itacademy.jpql.sologub.exception.CarException;
import by.itacademy.jpql.sologub.exception.CatException;
import by.itacademy.jpql.sologub.model.Car;
import by.itacademy.jpql.sologub.model.Cat;
import by.itacademy.jpql.sologub.model.Engine;

import java.util.List;

public class Main {
    public static void main(String[] args) throws CatException, CarException {
        catsExample();
        carAndEngineExample();
    }

    private static void carAndEngineExample() throws CarException {
        //Примеры по CRUD all операций через объект CarDao (на связке из Car и Engine)
        //Car и Engine one-to-one (отображение через car.hbn.xml и engine.hbn.xml)
        CarDao carDao = CarDaoJpaImpl.getInstance();

        List<Car> cars = carDao.getAll();//Пример по извлечению Car из бд
        cars.forEach(System.out::println);

        System.out.println(carDao.get(1));//Пример по извлечению Car из бд (с примапленными Engine)
        System.out.println(carDao.get("Ferrary"));
        System.out.println(carDao.get(1000));//такого нет в бд - придет null
        System.out.println(carDao.get("нету"));//такого нет в бд - придет null

        System.out.println(carDao.get("reno"));// такого ещё нет в бд - придет null
        Car carToInput = new Car() //Пример по добавлению обьекта Car в бд
                .withEngine(new Engine()
                        .withModel("v6_diesel")
                        .withPower(115))
                .withFirm("Volvo")
                .withSpeed(130);
        carDao.input(carToInput);
        System.out.println(carDao.get("Volvo"));// проверка, должен вернуть объект из бд

        Car carToChange = carDao.get("reno"); //Пример по изменению обьекта Car в бд
        System.out.println(carToChange); // перед изменением
        carToChange.setSpeed(144);
        Engine engineToChange = carToChange.getEngine();
        engineToChange.setPower(166);
        carDao.change(carToChange);
        System.out.println(carDao.get("reno")); // после изменения

        Car carToRemove = new Car()//Пример по удалению обьекта Car в бд
                .withEngine(new Engine()
                        .withModel("v64_turbo+")
                        .withPower(170))
                .withFirm("Toyota")
                .withSpeed(140);
        System.out.println("Результат удаления того, чего нет в базе " + carDao.remove(carToRemove));// будет false
        Car carFromDbToRemove = carDao.get(2);
        System.out.println("Результат удаления обьекта в базе " + carDao.remove(carFromDbToRemove));// будет true
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