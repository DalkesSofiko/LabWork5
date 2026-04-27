package commands;

import models.*;
import utils.InputHelper;
import Main.Main;
import java.util.HashMap;
import java.util.Deque;

/**
 * Команда count_greater_than_postal_address.
 * Выводит количество элементов коллекции, значение поля postalAddress которых больше заданного.
 * <p>
 * Сравнение производится лексикографически по полю street адреса.
 */
public class count_greater_than_postal_address extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public count_greater_than_postal_address() {
        super("count_greater_than_postal_address", "кол-во элементов с postalAddress > заданного");
    }

    /**
     * Выполняет команду: запрашивает у пользователя адрес для сравнения и выводит
     * количество организаций, чей почтовый адрес лексикографически больше введенного.
     *
     * @param argument аргументы команды (не используются в этой команде)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        Address target = new Address();
        target.setStreet(InputHelper.readString(Main.scanner, "street для сравнения: ", false));

        long count = collection.values().stream()
                .filter(o -> o.getPostalAddress().getStreet() != null && o.getPostalAddress().getStreet().compareTo(target.getStreet()) > 0)
                .count();

        System.out.println("Количество: " + count);
        addToHistory(getName(), history);
    }
}