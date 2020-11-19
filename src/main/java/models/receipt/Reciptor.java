package models.receipt;

import java.io.IOException;

public interface Reciptor {

    void create();

    void calcDiscount();

    void printReceipt(Boolean saveToFile) throws IOException;

    void getTotal();

    void calcVat();
}
