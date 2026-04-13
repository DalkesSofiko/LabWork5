package commands;

import models.Organization;
import java.util.HashMap;
import java.util.Deque;
import java.util.Comparator;

/**
 * Команда print_descending — выводит элементы в порядке убывания.
 */
public class print_descending extends AbstractCommand {

    public print_descending() {
        super("print_descending", "вывести элементы в порядке убывания");
    }

    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        if (collection.isEmpty()) { System.out.println("Пуста."); return; }
        collection.values().stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        addToHistory(getName(), history);
    }}