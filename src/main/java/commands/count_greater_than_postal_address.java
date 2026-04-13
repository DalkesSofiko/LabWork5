package commands;

import models.*;
import utils.InputHelper;
import Main.Main;
import java.util.HashMap;
import java.util.Deque;

/**
 * Команда count_greater_than_postal_address — считает элементы с адресом > заданного.
 */
public class count_greater_than_postal_address extends AbstractCommand {
    public count_greater_than_postal_address() {
        super("count_greater_than_postal_address", "кол-во элементов с postalAddress > заданного");
    }
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        Address target = new Address();
        target.setStreet(InputHelper.readString(Main.scanner, "street для сравнения: ", false));
        long count = collection.values().stream()
                .filter(o -> o.getPostalAddress().getStreet() != null && o.getPostalAddress().getStreet().compareTo(target.getStreet()) > 0)
                .count();
        System.out.println("Количество: " + count);
        addToHistory(getName(), history);
    }
}