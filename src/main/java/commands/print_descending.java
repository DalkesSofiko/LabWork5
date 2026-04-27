package commands;

import models.Organization;
import java.util.HashMap;
import java.util.Deque;
import java.util.Comparator;

/**
 * Команда print_descending.
 * Выводит элементы коллекции в порядке убывания согласно естественному порядку сортировки (по ID).
 */
public class print_descending extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public print_descending() {
        super("print_descending", "вывести элементы в порядке убывания");
    }

    /**
     * Выполняет команду: сортирует значения коллекции по убыванию и выводит их в стандартный поток вывода.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        if (collection.isEmpty()) {
            System.out.println("Пустая.");
            return;
        }

        collection.values().stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        addToHistory(getName(), history);
    }
}