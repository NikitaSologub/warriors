package by.itacademy.jpql.sologub;

import by.itacademy.jpql.sologub.dao.CarDao;
import by.itacademy.jpql.sologub.dao.CarDaoJpaImpl;
import by.itacademy.jpql.sologub.dao.CatDao;
import by.itacademy.jpql.sologub.dao.CatDaoJpaImpl;
import by.itacademy.jpql.sologub.dao.EntityManagerHelper;
import by.itacademy.jpql.sologub.dao.WeaponDao;
import by.itacademy.jpql.sologub.dao.WeaponDaoJpaImpl;
import by.itacademy.jpql.sologub.exception.CarException;
import by.itacademy.jpql.sologub.exception.CatException;
import by.itacademy.jpql.sologub.model.Car;
import by.itacademy.jpql.sologub.model.Cat;
import by.itacademy.jpql.sologub.model.Engine;
import by.itacademy.jpql.sologub.model.MilitaryFormation;
import by.itacademy.jpql.sologub.model.MilitaryRank;
import by.itacademy.jpql.sologub.model.Warrior;
import by.itacademy.jpql.sologub.model.WarriorInfo;
import by.itacademy.jpql.sologub.model.Weapon;
import by.itacademy.jpql.sologub.model.WeaponManufacturer;
import by.itacademy.jpql.sologub.model.WeaponType;
import org.hibernate.LazyInitializationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws CatException, CarException {
//        xmlMappingOnlyExamples();
        xmlAndAnnotationMappingExamples();
    }

    public static void xmlAndAnnotationMappingExamples() {
//        weapons();
        warriors();
    }

    public static void weapons() {
        WeaponDao weaponDao = WeaponDaoJpaImpl.getInstance();

        List<Weapon> weapons = weaponDao.getAll();
        weapons.forEach(System.out::println);

        System.out.println(weaponDao.get(1));
        System.out.println(weaponDao.get(100));//null expected
        System.out.println(weaponDao.get("sTR809_12093"));
        System.out.println(weaponDao.get("нет такго серийного номера"));//null expected

        Weapon w1 = weaponDao.get(1);
        w1.setType(WeaponType.AUTOMATIC_RIFLE);
        weaponDao.change(w1);

        Weapon w2 = new Weapon()
                .withType(WeaponType.GRENADE_LAUNCHER)
                .withManufacturer(WeaponManufacturer.STURM_RUGER_AND_CO_INC)
                .withSerialNumber("throw some numbers");
        System.out.println(weaponDao.get("throw some numbers"));//null expected
        weaponDao.input(w2);
        System.out.println(weaponDao.get("throw some numbers"));//object expected

        Weapon w3 = new Weapon()
                .withType(WeaponType.GUN)
                .withManufacturer(WeaponManufacturer.GENERAL_DYNAMICS_CORPORATION)
                .withSerialNumber("weapon to delete");
        weaponDao.input(w3);
        w3 = weaponDao.get("weapon to delete");//object expected
        System.out.println(w3);
        weaponDao.remove(w3);
    }

    private static void warriors() {
//        Warrior warrior1 = new Warrior()
//                .withInfo(new WarriorInfo()
//                        .withFirstname("Вечеслав")
//                        .withLastname("Капустин")
//                        .withAge(24))
//                .withRank(MilitaryRank.PRIVATE)
//                .withWeapon(new Weapon()
//                        .withManufacturer(WeaponManufacturer.FABRICAD_ARMI_PIETRO_BERETTA_S_P_A)
//                        .withType(WeaponType.SUB_MACHINE_GUN)
//                        .withSerialNumber("sTR918_12061"));
//        Warrior warrior2 = new Warrior()
//                .withInfo(new WarriorInfo()
//                        .withFirstname("Евгений")
//                        .withLastname("Плысак")
//                        .withAge(21))
//                .withRank(MilitaryRank.PRIVATE_1ST_CLASS)
//                .withWeapon(new Weapon()
//                        .withManufacturer(WeaponManufacturer.FABRICAD_ARMI_PIETRO_BERETTA_S_P_A)
//                        .withType(WeaponType.AUTOMATIC_RIFLE)
//                        .withSerialNumber("sTR809_12093"));
//        Warrior warrior3 = new Warrior()
//                .withInfo(new WarriorInfo()
//                        .withFirstname("Евгений")
//                        .withLastname("Науменко")
//                        .withAge(22))
//                .withRank(MilitaryRank.PRIVATE)
//                .withWeapon(new Weapon()
//                        .withManufacturer(WeaponManufacturer.LOCKHEED_MARTIN_CORPORATION)
//                        .withType(WeaponType.AUTOMATIC_RIFLE)
//                        .withSerialNumber("sTR800_12RTY1"));
//        Warrior warrior4 = new Warrior()
//                .withInfo(new WarriorInfo()
//                        .withFirstname("Валентин")
//                        .withLastname("Захаренко")
//                        .withAge(21))
//                .withRank(MilitaryRank.PRIVATE)
//                .withWeapon(new Weapon()
//                        .withManufacturer(WeaponManufacturer.FABRICAD_ARMI_PIETRO_BERETTA_S_P_A)
//                        .withType(WeaponType.RIFLE)
//                        .withSerialNumber("sTR670_120dy11"));
//        Warrior warrior5 = new Warrior()
//                .withInfo(new WarriorInfo()
//                        .withFirstname("Геннадий")
//                        .withLastname("Добкин")
//                        .withAge(23))
//                .withRank(MilitaryRank.PRIVATE)
//                .withWeapon(new Weapon()
//                        .withManufacturer(WeaponManufacturer.THALES_GROUP)
//                        .withType(WeaponType.MACHINE_GUN)
//                        .withSerialNumber("sTR123_147893"));
//        Warrior warrior6 = new Warrior()
//                .withInfo(new WarriorInfo()
//                        .withFirstname("Сергей")
//                        .withLastname("Круглов")
//                        .withAge(21))
//                .withRank(MilitaryRank.PRIVATE)
//                .withWeapon(new Weapon()
//                        .withManufacturer(WeaponManufacturer.NORTHROP_GRUMMAN_CORPORATION)
//                        .withType(WeaponType.AUTOMATIC_RIFLE)
//                        .withSerialNumber("sTR234_12725"));
//        Warrior warrior7 = new Warrior()
//                .withInfo(new WarriorInfo()
//                        .withFirstname("Сергей")
//                        .withLastname("Крукович")
//                        .withAge(18))
//                .withRank(MilitaryRank.NO_INSIGNIA)
//                .withWeapon(new Weapon()
//                        .withManufacturer(WeaponManufacturer.STURM_RUGER_AND_CO_INC)
//                        .withType(WeaponType.SNIPER_RIFLE)
//                        .withSerialNumber("sTR345_125343"));
//        Warrior warrior8 = new Warrior()
//                .withInfo(new WarriorInfo()
//                        .withFirstname("Андрей")
//                        .withLastname("Рыбников")
//                        .withAge(26))
//                .withRank(MilitaryRank.SERGEANT)
//                .withWeapon(new Weapon()
//                        .withManufacturer(WeaponManufacturer.HECHLER_AND_KOCH_GMBH)
//                        .withType(WeaponType.GUN)
//                        .withSerialNumber("sTR567_756593"));

//        MilitaryFormation formation1 = new MilitaryFormation()
//                .withTitle("36 Дорожно-мостовая бригада");
//        MilitaryFormation formation2 = new MilitaryFormation()
//                .withTitle("Военный клуб досуга любителей шахмат");
//        MilitaryFormation formation3 = new MilitaryFormation()
//                .withTitle("Сержантские курсы повышения квалификации");

//        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//        try {
//            List<Warrior> warriors;
//            TypedQuery<Warrior> typedQuery = manager.createQuery("from Warrior ", Warrior.class);
//            warriors = typedQuery.getResultList();

//            Set<Warrior> allSoldiers = new HashSet<>(warriors);
//            formation1.setWarriors(allSoldiers);
//
//            Set<Warrior> chessClub = new HashSet<>();
//            chessClub.add(warriors.get(1));
//            chessClub.add(warriors.get(2));
//            formation2.setWarriors(new HashSet<>(chessClub));

//            Set<Warrior> sergeantCourses = new HashSet<>();
//            sergeantCourses.add(warriors.get(3));
//            sergeantCourses.add(warriors.get(4));
//            formation3.setWarriors(sergeantCourses);

//            manager.persist(formation1);
//            manager.persist(formation2);
//            manager.persist(formation3);
//            manager.persist(warrior1);
//            manager.persist(warrior2);
//            manager.persist(warrior3);
//            manager.persist(warrior4);
//            manager.persist(warrior5);
//            manager.persist(warrior6);
//            manager.persist(warrior7);
//            manager.persist(warrior8);
//            transaction.commit();
//            System.out.println("Прошло успешно");
//        } catch (PersistenceException | IllegalStateException e) {
//            e.printStackTrace();
//            transaction.rollback();
//            System.out.println("Завершено ошибкой");
//        }
//        manager.close();
    }

    private static void xmlMappingOnlyExamples() throws CatException, CarException {
        catsExample();
        carExample();
        engineExample();
    }

    private static void engineExample() {
        //Car и Engine one-to-one (отображение через car.hbn.xml и engine.hbn.xml)
        //Пример выбрасывания LazyInitializationException
        EntityManager manager = EntityManagerHelper.getInstance().getEntityManager();
        System.out.println(manager);
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        List<Engine> engines;
        try {
            @SuppressWarnings("JpaQlInspection")
            TypedQuery<Engine> typedQuery = manager.createQuery("from Engine", Engine.class);
            engines = typedQuery.getResultList();
            System.err.println("List<Engine> извлечён из бд " + engines);
            transaction.commit();
        } catch (NoResultException e) {
            System.err.println("Не удалось достать List<Engine> из бд");
            engines = new ArrayList<>();
        } finally {
            manager.close();
        }

        try {
            Car car = engines.get(0).getCar();
            System.out.println(car);//этот код вызовет исключение
        } catch (LazyInitializationException e) {
            System.out.println("Получаем LazyInitializationException из-за ленивой инициализации");
        }
    }

    private static void carExample() throws CarException {
        //Примеры по CRUD all операций через объект CarDao (на связке из Car и Engine)
        //Car и Engine one-to-one (отображение через car.hbn.xml и engine.hbn.xml)
        CarDao carDao = CarDaoJpaImpl.getInstance();

        List<Car> cars = carDao.getAll();//Пример по извлечению Car из бд
        cars.forEach(System.out::println);

        System.out.println(carDao.get(1));//Пример по извлечению Car из бд (с примапленными Engine)
        System.out.println(carDao.get("Ferrary"));
        System.out.println(carDao.get(1000));//такого нет в бд - придет null
        System.out.println(carDao.get("нету"));//такого нет в бд - придет null

        System.out.println(carDao.get("Volvo"));// такого ещё нет в бд - придет null
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
        Car carFromDbToRemove = carDao.get(3);
        System.out.println("Результат удаления обьекта в базе " + carDao.remove(carFromDbToRemove));// будет true
    }

    private static void catsExample() throws CatException {
        // Пример простого одиночного bean Cat (отображение через cat.hbn.xml)
        CatDao catDao = CatDaoJpaImpl.getInstance();

        List<Cat> cats = catDao.getAll();//Пример по извлечению обьектов Cat из бд
        cats.forEach(c -> System.out.println("Котик " + c));

        catDao.input(new Cat()//Пример по добавлению нового Cat в бд
                .withName("Тюлень")
                .withColor("Пятнистый")
                .withAge(11));
        cats = catDao.getAll();
        cats.forEach(c -> System.out.println("Котик " + c));

        Cat experimental = catDao.get(1);//Пример по изменению Cat
        System.out.println("Котик до изменения " + experimental);
        experimental.setColor("Серо Буро Малиновый");
        catDao.change(experimental);
        System.out.println("Котик после изменения" + catDao.get(1));

        Cat toBeDeletedCat = catDao.get("гендальф");//Пример по удалению Cat
        System.out.println("Котик до удаления " + toBeDeletedCat);
        catDao.remove(toBeDeletedCat);
        System.out.println("Котик после удаления " + catDao.get("гендальф"));

        Cat pumba = catDao.get("пумба");//Пример по доставанию Cat по имени
        System.out.println("Котик по имени пумба " + pumba);
    }
}