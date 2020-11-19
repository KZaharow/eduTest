package models.receipt;

public interface Reciptor {
    void saveReceipt();

    void create();

    void calcDiscount();

    void printReceipt();

    void getTotal();
}
