package commands;

import java.nio.file.Files;
import java.nio.file.Paths;
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

        // Проверяем право на запись в файл перед сохранением
        if (!Files.isWritable(Paths.get(Main.filePath))) {
            System.out.println("Ошибка: Нет прав на запись в файл " + Main.filePath);
            System.out.println("Исправьте права: chmod u+w " + Main.filePath);
            return;
        }

        try {
            XMLHandler.save(Main.filePath, collection);
            System.out.println("Коллекция успешно сохранена в " + Main.filePath);
            addToHistory(getName(), history);
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
}