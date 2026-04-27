package commands;

import models.*;
import java.io.*;
import java.util.*;
import Main.Main;

/**
 * Команда execute_script.
 * Считывает и исполняет скрипт из указанного файла.
 * <p>
 * Каждая строка файла рассматривается как отдельная команда интерактивного режима.
 * Пустые строки и строки, начинающиеся с '#', игнорируются.
 */
public class execute_script extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public execute_script() {
        super("execute_script", "считать и исполнить скрипт из файла");
    }

    /**
     * Выполняет команду: открывает файл по указанному пути и последовательно выполняет
     * содержащиеся в нём команды через основной процессор команд.
     *
     * @param argument путь к файлу со скриптом
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (argument == null) {
            System.out.println("Добавьте к команде путь к файлу через пробел.");
            return;
        }

        File f = new File(argument);
        if (!f.exists() || !f.canRead()) {
            System.err.println("Файл недоступен.");
            return;
        }

        try (Scanner fs = new Scanner(f)) {
            while (fs.hasNextLine()) {
                String line = fs.nextLine().trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                Main.processCommand(line);
            }
            System.out.println("Скрипт выполнен.");
        } catch (Exception e) {
            System.err.println("Ошибка скрипта.");
        }
        addToHistory(getName(), history);
    }
}