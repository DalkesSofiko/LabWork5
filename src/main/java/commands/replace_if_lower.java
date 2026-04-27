package commands;

import models.*;
import utils.InputHelper;
import java.util.HashMap;
import java.util.Deque;
import java.util.Date;
import java.util.Scanner;
import Main.Main;

/**
 * Команда replace_if_lower.
 * Заменяет значение элемента коллекции по заданному ключу, если новое значение меньше старого.
 * <p>
 * Сравнение производится на основе реализации интерфейса Comparable (по ID).
 */
public class replace_if_lower extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public replace_if_lower() {
        super("replace_if_lower", "заменить по ключу, если новое меньше старого");
    }

    /**
     * Выполняет команду: считывает ID элемента и данные для новой организации.
     * Если новая организация "меньше" старой (согласно compareTo), заменяет её в коллекции,
     * сохраняя оригинальный ID и дату создания.
     *
     * @param argument аргумент команды (может содержать ID)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        Integer id = readId(Main.scanner, argument, "id для замены: ");

        if (!collection.containsKey(id)) {
            System.out.println("Не найден.");
            return;
        }

        Organization newO = readSample(Main.scanner);

        if (newO.compareTo(collection.get(id)) < 0) {
            newO.setId(id);
            newO.setCreationDate(collection.get(id).getCreationDate());
            collection.put(id, newO);
            System.out.println("Заменён.");
        } else {
            System.out.println("Новое не меньше старого.");
        }
        addToHistory(getName(), history);
    }

    /**
     * Считывает целочисленный ID из аргумента команды или запрашивает ввод у пользователя.
     *
     * @param sc сканер для чтения ввода
     * @param arg аргумент команды
     * @param prompt текст приглашения к вводу
     * @return корректный целочисленный ID
     */
    private static Integer readId(Scanner sc, String arg, String prompt) {
        if (arg != null && !arg.trim().isEmpty()) {
            try {
                return Integer.parseInt(arg.trim());
            } catch (Exception ignored) {
                // Если парсинг аргумента не удался, переходим к ручному вводу
            }
        }

        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Ожидается число.");
            }
        }
    }

    /**
     * Считывает данные для создания объекта Organization из консоли.
     * Используется для получения новых значений полей при замене.
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