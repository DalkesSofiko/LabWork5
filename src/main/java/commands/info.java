package commands;

import java.util.HashMap;
import java.util.Date;
import models.Organization;
import java.util.Deque;

/**
 * Команда info.
 * Выводит в стандартный поток вывода информацию о коллекции:
 * тип, дату инициализации (текущую) и количество элементов.
 */
public class info extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public info() {
        super("info", "вывод информации о коллекции");
    }

    /**
     * Выполняет команду: проверяет наличие элементов в коллекции и выводит
     * её метаданные (тип, дата, размер).
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) {
            return;
        }

        System.out.println("Тип коллекции: HashMap<Integer, Organization>");
        System.out.println("Дата инициализации: " + new Date());
        System.out.println("Количество элементов: " + collection.size());

        addToHistory(name, history);
    }
}