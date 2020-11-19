package printer;

import io.DataFileWriter;
import models.discountCard.DiscountCard;
import models.receipt.Iteam;
import models.vat.Vat;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Printer {
    // programmed into printer memory
    private final String HEADER = "             SUPERMARKET \"MARIO\"";
    private final String FOOTER = "                 THANK YOU";
    private final String LINE = "                                                ";
    private List<String> lines = new ArrayList<>();


    public void getHeader() {
        print(HEADER, Adjustment.LEFT);
        print(getDivider(), Adjustment.LEFT);
    }

    public void getFooter() {
        print(getDivider(), Adjustment.LEFT);
        print(FOOTER, Adjustment.LEFT);
    }

    public void print(String s, Adjustment e) {
        switch (e) {
            case LEFT: {
                lines.add(padRight(s, LINE.length()));
                break;
            }
            case RIGHT: {
                lines.add(padLeft(s, LINE.length()));
                break;
            }
        }
    }

    public void getItem(Iteam iteam, Adjustment e) {
        print(iteam.getName(), Adjustment.LEFT);
        print(String.format("%.2f*%.3f=%.2f(%s)", iteam.getPrice(), iteam.getQuantity(), iteam.getIteamTotal(), iteam.getVatGroup()), Adjustment.RIGHT);
        print((iteam.getDiscount() != 0) ? "discount=" + iteam.getDiscount() + "%" : "", Adjustment.RIGHT);
    }

    public void getVat(Vat vat, Adjustment e) {
        String s = (vat.getGroupAmount() > 0) ? String.format("vat %s (%.2f%s) = %.2f", vat.getGroup(), vat.getValue(), "%", vat.getGroupAmount()) : "";
        if (!s.isEmpty()) print(s, e);
    }

    public void getDiscount(DiscountCard discountCard) {
        print(getDivider(), Adjustment.LEFT);
        print("Card id: " + discountCard.getId() + ", discount = " + discountCard.getPercent() + "%", Adjustment.LEFT);
        print("Owner: " + discountCard.getName(), Adjustment.LEFT);
    }

    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    private String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    private String getDivider() {
        return LINE.replaceAll(" ", "-");
    }

    public void printout() {
        lines.stream().forEach(s -> System.out.println(s.substring(0, LINE.length())));
    }

    public void printoutToFile(Path path) throws IOException {
        new DataFileWriter().writeDataToFile(path, lines);
    }
}

