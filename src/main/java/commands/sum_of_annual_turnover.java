package commands;

import models.Organization;
import java.util.HashMap;
import java.util.Deque;

/**
 * Команда sum_of_annual_turnover.
 * Выводит в стандартный поток вывода сумму значений поля annualTurnover для всех элементов коллекции.
 */
public class sum_of_annual_turnover extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public sum_of_annual_turnover() {
        super("sum_of_annual_turnover", "вывести сумму annualTurnover");
    }

    /**
     * Выполняет команду: вычисляет сумму годовых оборотов всех организаций в коллекции
     * с использованием Stream API и выводит результат.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        if (!checkCollection(collection)) return;

        long sum = collection.values().stream()
                .mapToLong(Organization::getAnnualTurnover)
                .sum();

        System.out.println("Сумма: " + sum);
        addToHistory(getName(), history);
    }
}