package models.receipt;

import exceptions.ArticleException;
import exceptions.DiscountCardException;

import java.io.IOException;

/**
 * this interface uses for define base mathod which should be implemented during userReceipt construction
 */
public interface Receiptor {

    void create() throws ArticleException, DiscountCardException;

    void calcDiscount();

    void printReceipt(Boolean saveToFile) throws IOException;

    void getTotal();

    void calcVat();
}
