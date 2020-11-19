package printer;

import models.discountCard.DiscountCard;
import models.receipt.Iteam;

public class Printer {
    // programmed into printer memory
    private final String HEADER = "             SUPERMARKET \"MARIO\"";
    private final String FOOTER = "                 THANK YOU";
    private final String LINE = "                                                ";


    public void printHeader(){
        print(HEADER + '\n' + getDevider(), Adjustment.LEFT);
    }

    public void printFooter(){
        print(getDevider() + '\n' + FOOTER, Adjustment.LEFT);
    }

    public void print(String s, Adjustment e) {
        switch (e) {
            case LEFT: {
                System.out.println(padRight(s, LINE.length()));
                break;
            }
            case RIGHT: {
                System.out.println(padLeft(s, LINE.length()));
                break;
            }
        }
    }

    public void print(Iteam iteam, Adjustment e) {
        String s = String.format("%s",
                (iteam.getName() + LINE).substring(0, LINE.length()));
        s += padLeft(String.format("%.2f*%.3f=%.2f(%s)", iteam.getPrice(), iteam.getQuantity(), iteam.getIteamTotal(), iteam.getTax()), LINE.length());
        s += (iteam.getDiscount() != 0) ? padLeft("discount=" + iteam.getDiscount() + "%", LINE.length()) : "";
        System.out.println(s);
    }

    public void print(DiscountCard discountCard) {
        System.out.println(getDevider()
                + "\nCard id: " + discountCard.getId() + ", discount = " + discountCard.getPercent() + "%"
                + "\nOwner: " + discountCard.getName());
    }

    private String padRight(String s, int n) {
        return String.format("\n%-" + n + "s", s);
    }

    private String padLeft(String s, int n) {
        return String.format("\n%" + n + "s", s);
    }

    private String getDevider(){
        return LINE.replaceAll(" ", "-");
    }

}

