package commands;

import models.*;
import java.util.*;
import utils.InputHelper;
import Main.Main;
/**
 * Команда update — обновляет элемент по ID.
 */
public class update extends AbstractCommand {
    public update(){
        super("update", "обновление элемента коллекции по id");
    }

    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        Integer id = readId(Main.scanner, argument, "id для обновления: ");
        if (!collection.containsKey(id)) { System.out.println("❌ Не найден."); return; }
        Organization o = new Organization();
        o.setName(InputHelper.readString(Main.scanner, "name: ", false));
        Coordinates c = new Coordinates(); c.setX(InputHelper.readLong(Main.scanner, "coordinates.x (> -410): ", -410, null)); c.setY(InputHelper.readDouble(Main.scanner, "coordinates.y: "));
        o.setCoordinates(c);
        o.setAnnualTurnover(InputHelper.readLong(Main.scanner, "annualTurnover (> 0): ", 0, null));
        o.setFullName(InputHelper.readString(Main.scanner, "fullName: ", false));
        o.setType(InputHelper.readEnum(Main.scanner, "type (Enter=null): ", OrganizationType.class));
        Address a = new Address(); a.setStreet(InputHelper.readString(Main.scanner, "street (Enter=null): ", true));
        o.setPostalAddress(a);

        o.setId(id); o.setCreationDate(collection.get(id).getCreationDate());
        collection.put(id, o);
        System.out.println("Обновлён.");
        addToHistory(getName(), history);
    }
    private static Integer readId(Scanner sc, String arg, String prompt) {
        if (arg != null && !arg.trim().isEmpty()) try { return Integer.parseInt(arg.trim()); } catch(Exception ignored) {}
        while(true) { System.out.print(prompt); try { return Integer.parseInt(sc.nextLine().trim()); } catch(Exception e) { System.out.println("Ожидается число."); } }
    }
}