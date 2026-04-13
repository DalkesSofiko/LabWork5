package commands;

import java.util.HashMap;
import models.Organization;

public class help extends AbstractCommand{

    public help(){
        super("help", "Вывод справки по доступным командам");
    }

    @Override
    public void execute(String argument, HashMap<Integer, Organization> collection, java.util.Deque<String> history) {
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