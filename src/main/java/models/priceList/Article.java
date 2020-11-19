package models.priceList;

public class Article {

    private String code;
    private String name;
    private String tax;
    private double price;
    private int flags;

    public Article(String code, String name, String tax, double price, int flags) {
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
