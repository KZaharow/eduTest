package process;

import exceptions.ArticleException;
import exceptions.DiscountCardException;
import exceptions.VatException;

import java.io.IOException;

/**
 * interface uses as obligated note during UserReceipt construction
 */
public interface ReceiptCreator {
    void create() throws IOException, DiscountCardException, ArticleException, VatException;
}
