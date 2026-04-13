package commands;

import models.Organization;
import java.util.*;

/**
 * Команда show — выводит все элементы коллекции.
 */
public class show extends AbstractCommand {

    public show(){
        super("show", "вывод всех элементов коллекции");
    }

    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        if (collection.isEmpty()) System.out.println("Коллекция пустая.");
        else collection.values().forEach(System.out::println);
        addToHistory(getName(), history);
    }
}