package exceptions;

/**
 * Class uses for catch VatException exceptions
 * for example in case if input data does not match
 * specified (hardcored) format [0..99%]
 */
public class VatException extends Exception {
    private final String ERR_MSG;

    public VatException(String ERR_MSG) {
        this.ERR_MSG = ERR_MSG;
    }

    public void getMsg(){
        System.out.println(ERR_MSG);
    }
}
