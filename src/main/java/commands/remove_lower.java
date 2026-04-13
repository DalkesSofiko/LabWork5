package commands;

import models.*;
import utils.InputHelper;
import Main.Main;

import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Команда remove_lower — подготавливает данные.
 */
public class remove_lower extends AbstractCommand {

    public remove_lower() { super("remove_lower", "удалить элементы, меньшие заданного"); }
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        Organization sample = readSample(Main.scanner);
        collection.entrySet().removeIf(e -> e.getValue().compareTo(sample) < 0);
        System.out.println("Меньшие удалены.");
        addToHistory(getName(), history);
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