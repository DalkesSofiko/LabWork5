package Main;

import commands.*;
import models.Organization;
import java.io.File;
import java.util.*;
import models.*;
import utils.XMLHandler;

/**
 * Главное приложение. Содержит интерактивный цикл и маршрутизатор команд.
 */
public class Main {
    public static HashMap<Integer, Organization> collection = new HashMap<>();
    public static Deque<String> history = new ArrayDeque<>();
    public static Scanner scanner = new Scanner(System.in);
    public static String filePath = System.getenv("ORGANIZATIONS_FILE");
    public static int nextId = 1;
    public static boolean running = true;

    public static void addToHistory(String commandName) {
        if (history.size() >= 7) {
            history.pollFirst();
        }
        history.addLast(commandName);
    }
    /**
     * Маршрутизатор команд: вызывает execute(arg, collection, history)
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

    public static void main(String[] args) {

        if (filePath == null) {
            System.err.println("Ошибка: не задана переменная окружения ORGANIZATIONS_FILE");
            System.exit(1);
        }
        File f = new File(filePath);
        if (f.exists() && f.canRead()) {
            collection = XMLHandler.loadFromFile(filePath);
            if (!collection.isEmpty()) {
                nextId = collection.keySet().stream().mapToInt(i -> i).max().orElse(0) + 1;
            }
        }
        System.out.println("Приложение запущено.");
        processCommand("help");
        while (running) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue;
            try {
                processCommand(line);
                // Добавляем в историю только если команда выполнилась успешно
                addToHistory(line.split("\\s+")[0]);
            } catch (InputCancelledException e) {
                System.out.println("\nВвод отменён. Возврат в главное меню.");
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
}