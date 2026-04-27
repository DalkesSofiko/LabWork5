package commands;

import java.util.HashMap;
import models.Organization;
import java.util.Deque;

/**
 * Команда help.
 * Выводит справку по всем доступным командам приложения в стандартный поток вывода.
 */
public class help extends AbstractCommand {

    /**
     * Конструктор команды.
     * Инициализирует имя команды и её описание.
     */
    public help() {
        super("help", "Вывод справки по доступным командам");
    }

    /**
     * Выполняет команду: выводит отформатированный список всех поддерживаемых команд
     * с кратким описанием их назначения.
     *
     * @param argument аргументы команды (не используются)
     * @param collection коллекция организаций
     * @param history история выполненных команд
     */
    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, Deque<String> history) {
        System.out.println("""
                 --СПРАВКА ПО КОМАНДАМ--
                 help                              — показать эту справку  
                 info                              — информация о коллекции
                 show                              — вывести все элементы  
                 insert                            — добавить новый элемент
                 update id                         — обновить элемент по id
                 remove_key key                    — удалить по ключу      
                 clear                             — очистить коллекцию    
                 save                              — сохранить в файл      
                 execute_script file_name          — выполнить скрипт      
                 exit                              — завершить программу
                 remove_lower                      — удалить элементы <    
                 history                           — последние 7 команд    
                 replace_if_lower key              — заменить, если <      
                 sum_of_annual_turnover            — сумма annualTurnover  
                 count_greater_than_postal_address — количество с адресом> 
                 print_descending                  — элементы по убыванию  
                 cancel                            — выход в главное меню  
                 * — обязательные поля при вводе
                 Введите нужную команду:
                """);

        addToHistory(getName(), history);
    }
}