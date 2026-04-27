package commands;

import java.util.Deque;
import java.util.HashMap;
import models.*;
import utils.XMLHandler;
import Main.Main;

/**
 * Команда save.
 * Сохраняет текущее состояние коллекции организаций в файл, указанный в переменной окружения.
 */
public class save extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public save() {
        super("save", "сохранить коллекцию в файл");
    }

    /**
     * Выполняет команду: вызывает обработчик XML для записи данных коллекции в файл
     * и выводит сообщение об успешном сохранении.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        XMLHandler.save(Main.filePath, collection);
        System.out.println("Сохранена.");

        addToHistory(getName(), history);
    }
}