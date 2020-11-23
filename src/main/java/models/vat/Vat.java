package models.vat;

import exceptions.VatException;

/**
 * This class describes user Vat model where,
 * group - String (one char) value of selected vat
 * value - double value of vat [0..99]
 * groupAmount - sum double value of specified vat in the receipt
 */
public class Vat {

    private String group;
    private double value;
    private double groupAmount;

    public Vat(String group, double value, double groupAmount) throws VatException {
        if ((group.isEmpty()) || (group == null)) throw new VatException("vat group is not valid");
        if ((value < 0) || (value > 99.99)) throw new VatException("vat value out of the range 0..99");
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
