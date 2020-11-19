package models.vat;

public class Vat {

    private String group;
    private double value;
    private double groupAmount;

    public Vat(String group, double value, double groupAmount) {
        this.group = group;
        this.value = value;
        this.groupAmount = groupAmount;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getGroupAmount() {
        return groupAmount;
    }

    public void setVatGroupAmount(double groupAmount) {
        this.groupAmount = groupAmount;
    }
}
