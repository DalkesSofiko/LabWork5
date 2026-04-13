package commands;

import models.Organization;
import java.util.HashMap;
import java.util.Deque;

/**
 * Команда sum_of_annual_turnover — выводит сумму годового оборота.
 */
public class sum_of_annual_turnover extends AbstractCommand {

    public sum_of_annual_turnover() {
        super("sum_of_annual_turnover", "вывести сумму annualTurnover");
    }

    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;
        long sum = collection.values().stream().mapToLong(Organization::getAnnualTurnover).sum();
        System.out.println("Сумма: " + sum);
        addToHistory(getName(), history);
    }
}
