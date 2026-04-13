package commands;

import models.*;
import utils.InputHelper;
import java.util.HashMap;
import java.util.Deque;
import java.util.Date;
import java.util.Scanner;
import Main.Main;

/**
 * Команда replace_if_lower — заменяет элемент, если новый меньше.
 */
public class replace_if_lower extends AbstractCommand {

    public replace_if_lower() {
        super("replace_if_lower", "заменить по ключу, если новое меньше старого");
    }
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        Integer id = readId(Main.scanner, argument, "id для замены: ");
        if (!collection.containsKey(id)) { System.out.println("Не найден."); return; }
        Organization newO = readSample(Main.scanner);
        if (newO.compareTo(collection.get(id)) < 0) {
            newO.setId(id); newO.setCreationDate(collection.get(id).getCreationDate());
            collection.put(id, newO); System.out.println("Заменён.");
        } else System.out.println("Новое не меньше старого.");
        addToHistory(getName(), history);
    }
    private static Integer readId(Scanner sc, String arg, String prompt) {
        if (arg != null && !arg.trim().isEmpty()) try { return Integer.parseInt(arg.trim()); } catch(Exception ignored) {}
        while(true) { System.out.print(prompt); try { return Integer.parseInt(sc.nextLine().trim()); } catch(Exception e) { System.out.println("Ожидается число."); } }
    }
    private static Organization readSample(Scanner sc) {
        Organization o = new Organization();
        o.setName(InputHelper.readString(sc, "name: ", false));
        Coordinates c = new Coordinates(); c.setX(InputHelper.readLong(sc, "coordinates.x (> -410): ", -410, null)); c.setY(InputHelper.readDouble(sc, "coordinates.y: "));
        o.setCoordinates(c); o.setCreationDate(new Date());
        o.setAnnualTurnover(InputHelper.readLong(sc, "annualTurnover (> 0): ", 0, null));
        o.setFullName(InputHelper.readString(sc, "fullName: ", false));
        o.setType(InputHelper.readEnum(sc, "type (Enter=null): ", OrganizationType.class));
        Address a = new Address(); a.setStreet(InputHelper.readString(sc, "street (Enter=null): ", true)); o.setPostalAddress(a);
        return o;
    }
}