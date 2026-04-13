package commands;

import java.util.HashMap;
import java.util.Date;
import models.Organization;

/**
 * Команда info — выводит информацию о коллекции.
 */
public class info extends AbstractCommand {
    public info(){
        super("info", "вывод информации о коллекции");
    }

    @Override
    public void execute( String argument, HashMap<Integer, Organization> collection, java.util.Deque<String> history){
        if (!checkCollection(collection)){
            return;
        }

        System.out.println("Тип коллекции: HashMap<Integer, Organization>");
        System.out.println("Дата инициализации: " + new Date());
        System.out.println("Количество элементов: " + collection.size());

        addToHistory(name, history);
    }
}