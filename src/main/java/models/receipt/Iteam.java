package models.receipt;

import models.priceList.Article;

public class Iteam extends Article {

    private double discount;
    private double quantity;
    private double iteamTotal;

    public Iteam(String code, String name, String tax, double price, int flags, double discount, double quantity) {
        super(code, name, tax, price, flags);
        this.discount = discount;
        this.quantity = quantity;
        this.iteamTotal = price * quantity;
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

    public double getIteamTotal() {
        return iteamTotal;
    }

    public void setIteamTotal(double iteamTotal) {
        this.iteamTotal = iteamTotal;
    }

    @Override
    public String toString() {
        return String.format("%s \n%.2f * %.3f", getName(), getPrice(), getQuantity());
    }
}
