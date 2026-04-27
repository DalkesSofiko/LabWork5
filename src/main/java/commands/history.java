package commands;

import models.Organization;
import java.util.HashMap;
import java.util.Deque;

/**
 * Команда history.
 * Выводит в стандартный поток вывода последние 7 выполненных команд (без их аргументов).
 */
public class history extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public history() {
        super("history", "вывести последние 7 команд (без аргументов)");
    }

    /**
     * Выполняет команду: выводит содержимое истории команд или сообщение о том, что история пуста.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (history.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            history.forEach(System.out::println);
        }
        addToHistory(getName(), history);
    }
}