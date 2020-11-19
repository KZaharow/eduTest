package exceptions;

public class ReceiptException extends Exception {

    private String errorMsg;

    public ReceiptException(String message) {
        this.errorMsg = message;
    }

    public void getMsg(){
        System.out.println(errorMsg);
    }
}
