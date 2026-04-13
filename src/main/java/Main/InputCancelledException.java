package Main;

/**
 * Исключение для прерывания ввода и возврата в главное меню.
 */
public class InputCancelledException  extends RuntimeException {
    public InputCancelledException() {
        super("Ввод отменён.");
    }
}
