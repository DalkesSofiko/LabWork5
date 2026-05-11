package Main;

import commands.*;
import models.Organization;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.AccessDeniedException;
import java.util.*;
import models.*;
import utils.XMLHandler;

/**
 * Главное приложение. Содержит интерактивный цикл и маршрутизатор команд.
 * Управляет коллекцией организаций и историей команд.
 */
public class Main {

    /** Коллекция объектов Organization, управляемая программой. */
    public static HashMap<Integer, Organization> collection = new HashMap<>();

    /** История последних 7 выполненных команд. */
    public static Deque<String> history = new ArrayDeque<>();

    /** Сканер для чтения ввода пользователя из консоли. */
    public static Scanner scanner = new Scanner(System.in);

    /** Путь к файлу данных (задаётся при запуске). */
    public static String filePath;

    /** Счётчик для генерации уникальных ID новых объектов. */
    public static int nextId = 1;

    /** Флаг, определяющий, работает ли программа в данный момент. */
    public static boolean running = true;

    /**
     * Добавляет имя команды в историю выполнения.
     * Если история превышает 7 элементов, самый старый удаляется.
     *
     * @param commandName имя выполненной команды
     */
    public static void addToHistory(String commandName) {
        if (history.size() >= 7) {
            history.pollFirst();
        }
        history.addLast(commandName);
    }

    /**
     * Маршрутизатор команд: вызывает execute(arg, collection, history) для соответствующей команды.
     *
     * @param input строка ввода пользователя, содержащая имя команды и аргументы
     */
    public static void processCommand(String input) {
        String[] parts = input.trim().split("\\s+", 2);
        if (parts.length == 0) return;
        String cmdName = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : null;

        switch (cmdName) {
            case "help" -> new help().execute(argument, collection, history);
            case "info" -> new info().execute(argument, collection, history);
            case "show" -> new show().execute(argument, collection, history);
            case "insert" -> new insert().execute(argument, collection, history);
            case "update" -> new update().execute(argument, collection, history);
            case "remove_key" -> new remove_key().execute(argument, collection, history);
            case "clear" -> new clear().execute(argument, collection, history);
            case "save" -> new save().execute(argument, collection, history);
            case "execute_script" -> new execute_script().execute(argument, collection, history);
            case "exit" -> new exit().execute(argument, collection, history);
            case "remove_lower" -> new remove_lower().execute(argument, collection, history);
            case "history" -> new history().execute(argument, collection, history);
            case "replace_if_lower" -> new replace_if_lower().execute(argument, collection, history);
            case "sum_of_annual_turnover" -> new sum_of_annual_turnover().execute(argument, collection, history);
            case "count_greater_than_postal_address" -> new count_greater_than_postal_address().execute(argument, collection, history);
            case "print_descending" -> new print_descending().execute(argument, collection, history);
            default -> System.out.println("Неизвестная команда. Введите 'help'.");
        }
    }

    /**
     * Точка входа в приложение.
     * Инициализирует коллекцию из файла и запускает интерактивный режим.
     *
     * @param args аргументы командной строки (первый аргумент — путь к файлу)
     */
    public static void main(String[] args) {

        // ОПРЕДЕЛЕНИЕ ПУТИ
        //  аргумент командной строки
        if (args.length > 0 && args[0] != null && !args[0].trim().isEmpty()) {
            filePath = args[0].trim();
            System.out.println("Путь из аргумента: " + filePath);
        }
        // переменная окружения
        else if (System.getenv("ORGANIZATIONS_FILE") != null) {
            filePath = System.getenv("ORGANIZATIONS_FILE").trim();
            System.out.println("Путь из переменной окружения: " + filePath);
        }
        // интерактивный ввод
        else {
            System.out.print("Введите путь к файлу данных (ORGANIZATIONS_FILE): ");
            filePath = scanner.nextLine().trim();
            if (filePath.isEmpty()) {
                System.err.println("Путь не указан. Завершение работы.");
                System.exit(1);
            }
        }

        java.nio.file.Path filePathObj = Paths.get(filePath);
        File f = new File(filePath);

        //СОЗДАНИЕ ФАЙЛА, ЕСЛИ ЕГО НЕТ
        if (!f.exists()) {
            System.out.println("Файл не найден. Создаю: " + filePath);
            try {
                // Создаём родительские папки, если путь вложенный
                if (f.getParentFile() != null && !f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }

                // Проверяем право на запись в родительскую директорию
                if (f.getParentFile() != null) {
                    java.nio.file.Path parentPath = Paths.get(f.getParent());
                    if (!Files.isWritable(parentPath)) {
                        System.err.println("Ошибка: Нет прав на создание файла в директории " + f.getParent());
                        System.err.println("Исправьте права: chmod u+w " + f.getParent());
                        System.exit(1);
                    }
                }

                f.createNewFile();
                Files.writeString(filePathObj, "<collection></collection>");
                System.out.println("Файл создан и инициализирован.");
            } catch (AccessDeniedException e) {
                System.err.println("Ошибка: Нет прав на запись. Программа завершена.");
                System.exit(1);
            } catch (Exception e) {
                System.err.println("Ошибка при создании файла: " + e.getMessage());
                System.exit(1);
            }
        }

        // ПРОВЕРКА ПРАВА НА ЧТЕНИЕ ПЕРЕД ЗАГРУЗКОЙ
        if (!Files.isReadable(filePathObj)) {
            System.err.println("Ошибка: Нет прав на чтение файла " + filePath);
            System.err.println("Исправьте права: chmod u+r " + filePath);
            System.exit(1);
        }

        // ЗАГРУЗКА КОЛЛЕКЦИИ
        try {
            collection = XMLHandler.loadFromFile(filePath);
            if (!collection.isEmpty()) {
                nextId = collection.keySet().stream().mapToInt(i -> i).max().orElse(0) + 1;
            }
            System.out.println("Данные загружены из " + filePath);
        } catch (Exception e) {
            System.err.println("Ошибка чтения XML: " + e.getMessage());
            System.err.println("Коллекция будет пустой. Проверьте целостность файла.");
            collection = new HashMap<>();
        }

        System.out.println("Приложение запущено.");
        processCommand("help");

        // ИНТЕРАКТИВНЫЙ ЦИКЛ
        while (running) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue;
            try {
                processCommand(line);
                addToHistory(line.split("\\s+")[0]);
            } catch (InputCancelledException e) {
                System.out.println("\nВвод отменён. Возврат в главное меню.");
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
}