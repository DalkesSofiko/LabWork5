package commands;

import models.*;
import java.util.*;
import Main.Main;

/**
 * Команда remove_key.
 * Удаляет элемент из коллекции по его уникальному ключу (ID).
 */
public class remove_key extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public remove_key() {
        super("remove_key", "удалить элемент из коллекции по его ключу");
    }

    /**
     * Выполняет команду: считывает ID элемента и удаляет его из коллекции, если он существует.
     *
     * @param argument аргумент команды (может содержать ID)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        Integer id = readId(Main.scanner, argument, "id для удаления: ");

        if (collection.remove(id) != null) {
            System.out.println("Удалён.");
        } else {
            System.out.println("Не найден.");
        }
        addToHistory(getName(), history);
    }

    /**
     * Считывает целочисленный ID из аргумента команды или запрашивает ввод у пользователя,
     * если аргумент отсутствует или некорректен.
     *
     * @param sc сканер для чтения ввода
     * @param arg аргумент команды
     * @param prompt текст приглашения к вводу
     * @return корректный целочисленный ID
     */
    private static Integer readId(Scanner sc, String arg, String prompt) {
        if (arg != null && !arg.trim().isEmpty()) {
            try {
                return Integer.parseInt(arg.trim());
            } catch (Exception ignored) {
                // Если парсинг аргумента не удался, переходим к ручному вводу
            }
        }

        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Ожидается число.");
            }
        }
    }
}