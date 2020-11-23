package printer;

import io.DataFileWriter;
import models.discountCard.DiscountCard;
import models.receipt.Item;
import models.vat.Vat;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * class uses for adjust and printout receipt to the console.
 * Due printout is hard system process, printout task has been
 * implemented like separated Thread
 *
 * Also printer has a method for printout data to the file
 */
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

    public void getItem(Item item, Adjustment e) {
        String s = (item.getName() + LINE).substring(0, LINE.length());
        print(s, Adjustment.LEFT);
        print(String.format("%.2f*%.3f=%.2f(%s)", item.getPrice(), item.getQuantity(), item.getItemTotal(), item.getVatGroup()), Adjustment.RIGHT);
        print((item.getDiscount() != 0) ? "discount=" + item.getDiscount() + "%" : "", Adjustment.RIGHT);
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
        Runnable printerTask = new Runnable() {
            @Override
            public void run() {
                lines.stream().forEach(s -> System.out.println(s));
            }
        };
        new Thread(printerTask).start();
    }

    public void printoutToFile(Path path) throws IOException {
        new DataFileWriter().writeDataToFile(path, lines);
    }
}

