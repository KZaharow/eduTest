import process.TaskHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TaskHandler taskHandler = new TaskHandler(args);
        try {
            taskHandler.create();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*taskHandler.printoutReceipt();
        taskHandler.saveReceiptCopy();*/
    }
}
