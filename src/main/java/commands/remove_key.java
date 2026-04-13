package commands;

import models.*;
import java.util.*;
import Main.Main;
/**
 * Команда remove_key — удаляет элемент по ключу.
 */
public class remove_key extends AbstractCommand {
    public remove_key(){
        super("remove_key", "удалить элемент из коллекции по его ключу");
    }

    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        Integer id = readId(Main.scanner, argument, "id для удаления: ");
        if (collection.remove(id) != null) System.out.println("Удалён.");
        else System.out.println("Не найден.");
        addToHistory(getName(), history);
    }
    private static Integer readId(Scanner sc, String arg, String prompt) {
        if (arg != null && !arg.trim().isEmpty()) try { return Integer.parseInt(arg.trim()); } catch(Exception ignored) {}
        while(true) { System.out.print(prompt); try { return Integer.parseInt(sc.nextLine().trim()); } catch(Exception e) { System.out.println("❌ Ожидается число."); } }
    }
}