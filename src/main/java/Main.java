import exceptions.ArticleException;
import exceptions.DiscountCardException;
import exceptions.VatException;
import process.TaskHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TaskHandler taskHandler = new TaskHandler(args);
        try {
            taskHandler.create();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DiscountCardException e) {
            e.printStackTrace();
            e.getMsg();
        } catch (VatException e) {
            e.printStackTrace();
            e.getMsg();
        } catch (ArticleException e) {
            e.printStackTrace();
            e.getMsg();
        }
    }
}
