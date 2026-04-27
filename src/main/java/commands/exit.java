package commands;

import models.*;
import Main.Main;
import java.util.Deque;
import java.util.HashMap;

/**
 * Команда exit.
 * Завершает работу программы.
 * <p>
 * Данные не сохраняются в файл автоматически при выполнении этой команды.
 */
public class exit extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Выполняет команду: устанавливает флаг завершения работы приложения в false
     * и выводит сообщение о завершении.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        Main.running = false;
        System.out.println("Программа завершена.");
    }
}