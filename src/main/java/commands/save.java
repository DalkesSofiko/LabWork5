package commands;

import java.util.Deque;
import java.util.HashMap;
import models.*;
import utils.XMLHandler;
import Main.Main;

/**
 * Команда save — сохраняет коллекцию в файл.
 */
public class save extends AbstractCommand {

    public save() { super("save", "сохранить коллекцию в файл"); }
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        XMLHandler.save(Main.filePath, collection);
        System.out.println("Сохранена.");
        addToHistory(getName(), history);
    }
}