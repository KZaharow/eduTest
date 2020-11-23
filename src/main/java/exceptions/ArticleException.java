package exceptions;

/**
 * Class uses for catch ArticleException exceptions
 * for example in case if input data or data from the price list does not match
 * specified (hardcored) format
 */
public class ArticleException extends Exception {
    private final String ERR_MSG;

    public ArticleException(String ERR_MSG) {
        this.ERR_MSG = ERR_MSG;
    }

    public void getMsg(){
        System.out.println(ERR_MSG);
    }
}
