package by.itacademy.jpql.sologub;

import by.itacademy.jpql.sologub.dao.CarDao;
import by.itacademy.jpql.sologub.dao.CarDaoJpaImpl;
import by.itacademy.jpql.sologub.dao.CatDao;
import by.itacademy.jpql.sologub.dao.CatDaoJpaImpl;
import by.itacademy.jpql.sologub.dao.EntityManagerHelper;
import by.itacademy.jpql.sologub.dao.MilitaryFormationDao;
import by.itacademy.jpql.sologub.dao.MilitaryFormationJpaImpl;
import by.itacademy.jpql.sologub.dao.WarriorDao;
import by.itacademy.jpql.sologub.dao.WarriorDaoJpaImpl;
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
//        warriors();
//        groups();
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
        WarriorDao warriorDao = WarriorDaoJpaImpl.getInstance();

        List<Warrior> warriors = warriorDao.getAll();
        warriors.forEach(System.out::println);

        System.out.println(warriorDao.get(10));//object expected
        System.out.println(warriorDao.get(100000));//null expected
        System.out.println(warriorDao.get("Плысак"));//object expected
        System.out.println(warriorDao.get("такой фамилии нету"));//null expected

        Warrior warriorToInput = new Warrior()
                .withInfo(new WarriorInfo()
                        .withFirstname("Нурсултан")
                        .withLastname("Тюлякбаев")
                        .withAge(20))
                .withRank(MilitaryRank.PRIVATE_1ST_CLASS)
                .withWeapon(new Weapon()
                        .withManufacturer(WeaponManufacturer.THALES_GROUP)
                        .withType(WeaponType.RIFLE)
                        .withSerialNumber("leko_2026_slvr441"));
        warriorDao.input(warriorToInput);
        System.out.println(warriorDao.get("Тюлякбаев"));

        Warrior warriorToChange = warriorDao.get(13);
        System.out.println(warriorToChange);
        warriorToChange.setRank(MilitaryRank.GENERAL_OF_THE_ARMY);
        warriorToChange.getInfo().setAge(45);
        warriorToChange.getWeapon().setSerialNumber("123");
        warriorDao.change(warriorToChange);
        System.out.println(warriorDao.get(13));

        Warrior warriorToDelete = warriorDao.get(20);
        System.out.println(warriorToDelete); // object expected
        warriorDao.remove(warriorToDelete);
        System.out.println(warriorDao.get(20));// null expected
    }

    private static void groups() {
        MilitaryFormationDao formationDao = MilitaryFormationJpaImpl.getInstance();

        List<MilitaryFormation> formations = formationDao.getAll();
        formations.forEach(System.out::println);


        System.out.println(formationDao.get(13));//object expected
        System.out.println(formationDao.get(1000));//null expected
        System.out.println(formationDao.get("36 Дорожно-мостовая бригада"));//object expected
        System.out.println(formationDao.get("нет такоей группы"));//null expected


        System.out.println(formationDao.get("Произвольная группа для добавления"));//null expected
        formationDao.input(new MilitaryFormation()
                .withTitle("Произвольная группа для добавления")
                .withWarriors(formations.get(0).getWarriors()
                        .stream()
                        .limit(1)
                        .collect(Collectors.toSet())));
        System.out.println(formationDao.get("Произвольная группа для добавления"));//object expected


        groupsChanging();//изменения полей группы и ее обьектов


        formationDao.input(new MilitaryFormation()
                .withTitle("Произвольная группа для удаления")
                .withWarriors(formations.get(0).getWarriors()
                        .stream()
                        .skip(2)
                        .limit(2)
                        .collect(Collectors.toSet())));
        MilitaryFormation formationToDelete = formationDao.get("Произвольная группа для удаления");
        System.out.println(formationToDelete); // object expected
        formationDao.remove(formationToDelete);
        System.out.println(formationDao.get("Произвольная группа для удаления"));// null expected
    }

    private static void groupsChanging(){
        //Большое тело - вынес отдельно в метод
        MilitaryFormationDao formationDao = MilitaryFormationJpaImpl.getInstance();

        //Создаем группу с солдатом которую будем изменять
        MilitaryFormation formationToChange = new MilitaryFormation()
                .withTitle("Произвольная группа для изменения")
                .withWarriors(Set.of(new Warrior()
                        .withInfo(new WarriorInfo()
                                .withFirstname("Герман")
                                .withLastname("Стерлингов")
                                .withAge(28))
                        .withRank(MilitaryRank.NO_INSIGNIA)
                        .withWeapon(new Weapon()
                                .withManufacturer(WeaponManufacturer.THALES_GROUP)
                                .withType(WeaponType.AUTOMATIC_RIFLE)
                                .withSerialNumber("38оа923во28TR_OC45445"))));

        //Добавляем ее и сразу же смотрим как она легла в БД
        formationDao.input(formationToChange);
        formationToChange = formationDao.get("Произвольная группа для изменения");
        System.out.println(formationToChange);//object show 1st state

        //Меняем данные созданной группы
        formationToChange.setTitle("Произвольная группа уже изменена!!!");//меняем поля самой группы
        Warrior w1 = formationToChange.getWarriors().stream()
                .findAny().orElseThrow();
        w1.setRank(MilitaryRank.SERGEANT);//меняем поле объекта в SET группы

        //Изменить состояние и вывести как легли изменения в бд
        formationDao.change(formationToChange);
        System.out.println(formationDao.get("Произвольная группа уже изменена!!!"));//object show 2nd state
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