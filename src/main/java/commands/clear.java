package commands;

import models.*;
import java.util.*;

/**
 * Команда clear — очищает коллекцию.
 */
public class clear extends AbstractCommand {

    public clear() { super("clear", "очистить коллекцию"); }
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        collection.clear(); System.out.println("Очищена.");
        addToHistory(getName(), history);
    }
}