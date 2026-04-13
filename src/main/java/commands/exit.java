package commands;

import models.*;
import Main.Main;
import java.util.Deque;
import java.util.HashMap;

/**
 * Команда exit — завершает программу.
 */
public class exit extends AbstractCommand {

    public exit() { super("exit", "завершить программу (без сохранения в файл)"); }
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        Main.running = false;
        System.out.println("Программа завершена.");
    }
}