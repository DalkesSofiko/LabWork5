package commands;

import models.*;
import java.util.*;
import utils.InputHelper;
import Main.Main;

/**
 * Команда insert.
 * Добавляет новый элемент в коллекцию с автоматически сгенерированным ключом (ID).
 * <p>
 * Запрашивает у пользователя ввод всех полей объекта Organization через консоль.
 */
public class insert extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public insert() {
        super("insert", "добавление нового элемента с заданным ключом");
    }

    /**
     * Выполняет команду: считывает данные для новой организации из консоли,
     * создает объект, присваивает ему уникальный ID и добавляет в коллекцию.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        Organization o = new Organization();
        o.setName(InputHelper.readString(Main.scanner, "name: ", false));

        Coordinates c = new Coordinates();
        c.setX(InputHelper.readLong(Main.scanner, "coordinates.x (> -410): ", -410, null));
        c.setY(InputHelper.readDouble(Main.scanner, "coordinates.y: "));
        o.setCoordinates(c);

        o.setCreationDate(new Date());
        o.setAnnualTurnover(InputHelper.readLong(Main.scanner, "annualTurnover (> 0): ", 0, null));
        o.setFullName(InputHelper.readString(Main.scanner, "fullName: ", false));
        o.setType(InputHelper.readEnum(Main.scanner, "type (Enter=null): ", OrganizationType.class));

        Address a = new Address();
        a.setStreet(InputHelper.readString(Main.scanner, "street (Enter=null): ", true));
        o.setPostalAddress(a);

        o.setId(Main.nextId++);
        collection.put(o.getId(), o);
        System.out.println("Добавлен с id=" + o.getId());
        addToHistory(getName(), history);
    }
}