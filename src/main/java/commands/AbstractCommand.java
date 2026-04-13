package commands;
import models.*;
import commands.*;

import java.util.HashMap;

/**
 * Абстрактный класс для всех команд приложения, содержит общую логику
 */
public abstract class AbstractCommand {
    protected final String name;
    protected final String description;

    /**
     * Создает команду с указанным именем и описанием
     * @param name имя команды
     * @param description описание для справки
     */
    public AbstractCommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    /**
     * Выполнение команды, реализация уже в конктретной команде
     * @param argument аргумент команды
     * @param collection коллекция организации
     * @param history очередь истории команд
     */
    public abstract void execute(String argument, HashMap<Integer, Organization> collection, java.util.Deque<String> history);

    /**
     * Вспомогательный метод, проверяем что коллекция не null
     * @param collection название коллекции
     * @return результат проверки
     */
    protected boolean checkCollection(HashMap<Integer, Organization> collection){
        if(collection == null){
            System.err.println("Ошибка! Коллекция не инициализирована");
            return false;
        }
        return true;
    }

    /**
     * Вспомогаетльный метод, добавляющий команду в историю
     * @param commandName
     * @param history
     */
    protected void addToHistory(String commandName, java.util.Deque<String> history){
        if(history.size() >= 7) { //history : вывести последние 7 команд (без их аргументов)
            history.pollFirst();
        }
        history.addLast(commandName);
    }
}
