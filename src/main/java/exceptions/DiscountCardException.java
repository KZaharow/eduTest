package exceptions;

/**
 * Class uses for catch DiscountCardException exceptions
 * for example in case if input data or data from the d-card list does not match
 * specified (hardcored) format
 */
public class DiscountCardException extends Exception {
    private final String ERR_MSG;

    public DiscountCardException(String ERR_MSG) {
        this.ERR_MSG = ERR_MSG;
    }

    public void getMsg() {
        System.out.println(ERR_MSG);
    }
}
