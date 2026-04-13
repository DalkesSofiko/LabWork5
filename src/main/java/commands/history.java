package commands;

import models.Organization;
import java.util.HashMap;
import java.util.Deque;

/**
 * Команда history — выводит последние 7 команд.
 */
public class history extends AbstractCommand {

    public history() {
        super("history", "вывести последние 7 команд (без аргументов)");
    }
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (history.isEmpty()) System.out.println("История пуста.");
        else history.forEach(System.out::println);
        addToHistory(getName(), history);
    }
}