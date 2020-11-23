package models.receipt;

import exceptions.ArticleException;
import models.priceList.Article;

/**
 * This class Item create position of receipt and extend Article
 *
 * discount - calculated position of current item discount value
 * this param is SUM of each discount card program
 *
 * quantity - item position quantity from the specified use data
 *
 * itemTotal - multiplication of the price and quantity
 */

public class Item extends Article {

    private double discount;
    private double quantity;
    private double itemTotal;

    public Item(String code, String name, String tax, double price, int flags, double discount, double quantity) throws ArticleException {
        super(code, name, tax, price, flags);
        this.discount = discount;
        this.quantity = quantity;
        this.itemTotal = price * quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    @Override
    public String toString() {
        return String.format("%s \n%.2f * %.3f", getName(), getPrice(), getQuantity());
    }
}
