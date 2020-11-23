package models.priceList;

import exceptions.ArticleException;

/**
 * This class describes user Article model where,
 * code - String barcode, length {1..13} EAN128, EAN 13 etc
 * name - String article name, name length cut by the class Printer
 * in accordance with specified printer settings
 * tax - String letter [A..Z] which relay to the tax data base
 * price - positive double value of article price
 * flags - bit field, each bit adjust specified discount card program
 */
public class Article {

    private String code;
    private String name;
    private String tax;
    private double price;
    private int flags;

    public Article(String code, String name, String tax, double price, int flags) throws ArticleException {
        if (price <= 0) {
            throw new ArticleException("Wrong price value for article code = " + code);
        }
        this.code = code;
        this.name = name;
        this.tax = tax;
        this.price = price;
        this.flags = flags;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatGroup() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

}
