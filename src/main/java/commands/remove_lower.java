package commands;

import models.*;
import utils.InputHelper;
import Main.Main;

import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Команда remove_lower.
 * Удаляет из коллекции все элементы, которые меньше заданного пользователем элемента.
 * <p>
 * Сравнение производится на основе реализации интерфейса Comparable (по ID).
 */
public class remove_lower extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public remove_lower() {
        super("remove_lower", "удалить элементы, меньшие заданного");
    }

    /**
     * Выполняет команду: считывает данные для эталонной организации, сравнивает с ней
     * все элементы коллекции и удаляет те, что меньше эталона.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        Organization sample = readSample(Main.scanner);
        collection.entrySet().removeIf(e -> e.getValue().compareTo(sample) < 0);

        System.out.println("Меньшие удалены.");
        addToHistory(getName(), history);
    }

    /**
     * Считывает данные для создания эталонного объекта Organization из консоли.
     * Используется для сравнения с элементами коллекции.
     *
     * @param sc сканер для чтения ввода
     * @return объект Organization с данными, введенными пользователем
     */
    private static Organization readSample(Scanner sc) {
        Organization o = new Organization();
        o.setName(InputHelper.readString(sc, "name: ", false));

        Coordinates c = new Coordinates();
        c.setX(InputHelper.readLong(sc, "coordinates.x (> -410): ", -410, null));
        c.setY(InputHelper.readDouble(sc, "coordinates.y: "));
        o.setCoordinates(c);

        o.setCreationDate(new Date());
        o.setAnnualTurnover(InputHelper.readLong(sc, "annualTurnover (> 0): ", 0, null));
        o.setFullName(InputHelper.readString(sc, "fullName: ", false));
        o.setType(InputHelper.readEnum(sc, "type (Enter=null): ", OrganizationType.class));

        Address a = new Address();
        a.setStreet(InputHelper.readString(sc, "street (Enter=null): ", true));
        o.setPostalAddress(a);

        return o;
    }
}