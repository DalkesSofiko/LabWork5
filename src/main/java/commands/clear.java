package commands;

import models.*;
import java.util.*;

/**
 * Команда clear.
 * Очищает коллекцию, удаляя все хранящиеся в ней элементы.
 */
public class clear extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public clear() {
        super("clear", "очистить коллекцию");
    }

    /**
     * Выполняет команду: полностью очищает коллекцию организаций и выводит сообщение об успехе.
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        collection.clear();
        System.out.println("Очищена.");

        addToHistory(getName(), history);
    }
}