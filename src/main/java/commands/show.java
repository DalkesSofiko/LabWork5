package commands;

import models.Organization;
import java.util.*;

/**
 * Команда show.
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 */
public class show extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public show() {
        super("show", "вывод всех элементов коллекции");
    }

    /**
     * Выполняет команду: проверяет наличие элементов в коллекции и выводит их список
     * или сообщение о том, что коллекция пуста.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        if (collection.isEmpty()) {
            System.out.println("Коллекция пустая.");
        } else {
            collection.values().forEach(System.out::println);
        }
        addToHistory(getName(), history);
    }
}