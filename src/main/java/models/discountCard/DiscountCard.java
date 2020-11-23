package models.discountCard;

import exceptions.DiscountCardException;

/**
 * This class describes user discount card model where,
 * id - String disc. card code, length {1..10}
 * name - String dcard owner's name
 * percent - [0 .. 99.99] min/max dcard %
 */
public class DiscountCard {
    private String id;
    private String name;
    private double percent;

    public DiscountCard(String id, String name, double percent) throws DiscountCardException {
        if ((percent <= 0) || (percent > 99.99)) throw new DiscountCardException("Given discount card percent = " + percent + "% out of the range 0.01..99.99%");
        this.id = id;
        this.name = name;
        this.percent = percent;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public double getPercent() {
        return percent;
    }


}
