package utils;
import java.util.Scanner;

/**
 * класс, который позволяет вводить данные с консоли безопасно
 * все методы статистические, класс не создается через new
 */
public class InputReader {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * принимаем строку и проверяем ее на пустоту
     * @param prompt сообщение-подсказка для пользователя
     * @param nullable переменная, указывающая на то, можно ли вводить пустую строку
     * @param notEmpty если true, то пустую строку не принимаем
     * @return введенную строку или null
     */
    public static String readString(String prompt, boolean nullable, boolean notEmpty){
        while (true){ //while используется чтобы цикл был бесконечным до того момента, пока пользователь не введ то, что от него требуется
            System.out.println(prompt); //выводим подсказку с тем что нужно ввести
            String input = scanner.nextLine();

            if(nullable && input.isEmpty()){
                return null;
            }
            else if(notEmpty && input.trim().isEmpty()){
                System.out.println("Поле не может быть пустым, введите корректное значение");
                continue;
            }
            return input.trim();
        }
    }

    /**
     * Принимаем целое число (если не целое, выводим ошибку) в заданном диапазоне
     * @param prompt подсказка для пользователя
     * @param min минимальное значение (если его нет, то null)
     * @param max максимальное значение (если его нет, то null)
     * @return введенное число
     */
    public static int reafInt(String prompt, Integer min, Integer max){
        while (true){
            System.out.println(prompt);
            String input = scanner.nextLine();

            if (input.isEmpty()){
                return 0;
            }

            try {
                int value = Integer.parseInt(input);

                if (min != null && value < min) {
                    System.out.println("Число должно быть не меньше " + min);
                } else if (max != null && value > max) {
                    System.out.println("Число должно быть не больше " + max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e){
                System.out.println("Ошибка! Необходимо ввести целое число");
            }
        }
    }

    /**
     * Принимаем целое число (long) (если не целое, выводим ошибку) в заданном диапазоне
     * @param prompt подсказка для пользователя
     * @param min минимальное значение (если его нет, то null)
     * @return введенное число
     */
    public static long readLong(String prompt, Long min){
        while (true){
            System.out.println(prompt);
            String input = scanner.nextLine();

            if (input.isEmpty()){
                return 0;
            }

            try {
                long value = Long.parseLong(input);

                if (min != null && value <= min){
                    System.out.println("Число должно быть больше " + min);
                    continue;
                }
                return value;
            } catch (NumberFormatException e){
                System.out.println("Ошибка! Необходимо ввести целое число!");
            }
        }
    }

    /**
     * Читает значение перечисления (enum) по имени константы
     * @param promt подсказка пользователю
     * @param enumClass класс перечисления (здесь OrganizationType.class)
     * @return выбранная константа или null
     * @param <T> тип перечисления
     */
    public static <T extends Enum<T>> T readEnum(String promt, Class<T> enumClass){
        System.out.println("Список Типов компаний: ");
        T[] constants = enumClass.getEnumConstants();
        for (int i=0; i<constants.length; i++){
            System.out.println(constants[i].name());
        }
        System.out.println();

        while (true){
            System.out.println(promt);
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()){
                return null;
            }

            try{
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e){
                System.out.println("Неподходящее значение. Попробуйте снова");
            }
        }
    }

    /**
     * Читаем переменную double, число для коорд y
     * @param promt подсказка пользователю
     * @return координата или null
     */
    public static double readDouble(String promt) {
        while (true){
            System.out.println(promt);
            String input = scanner.nextLine();

            if(input.isEmpty()){
                return 0.0;
            }

            try {
                return Double.parseDouble(input);
            } catch (IllegalArgumentException e){
                System.out.println("Ошибка! Введите корректное число!");
            }
        }
    }

    /**
     * прекращаем работу сканера
     */
    public static void close(){
        scanner.close();
    }
}
