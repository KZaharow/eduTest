package process;

import exceptions.ArticleException;
import exceptions.DiscountCardException;
import models.receipt.Item;
import models.vat.Vat;
import printer.Adjustment;
import printer.Printer;
import models.receipt.Receiptor;
import models.priceList.Article;
import models.discountCard.DiscountCard;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class UserReceipt implements Receiptor {


    private final double DISCOUNT_QTY_LIMIT = 5; // 5.000
    private final double DISCOUNT_PRC_LIMIT = 10; //%
    private final Path path;
    private double total;
    private double totalDiscount;
    private DiscountCard discountCard;
    private String[] inputArrayString;
    private Map<String, Article> priceList;
    private Map<String, Item> items;
    private Map<String, DiscountCard> discountCardsList;
    private Map<String, Vat> vats;

    public UserReceipt(String[] inputArrayString, Map<String, Article> priceList, Map<String, DiscountCard> discountCardsList, Map<String, Vat> vats, Path path) {
        this.inputArrayString = inputArrayString;
        this.priceList = priceList;
        this.discountCardsList = discountCardsList;
        this.vats = vats;
        this.path = path;
    }

    @Override
    public void create() throws ArticleException, DiscountCardException {
        items = getItemsList(inputArrayString);
        discountCard = getUserDiscountCard(inputArrayString);
    }

    private DiscountCard getUserDiscountCard(String[] userInputData) throws DiscountCardException {

        // get user discount card data
        for (String s : userInputData) {

            if (s.matches("#\\d{1,10}")) {
                String[] arr = s.split("#");
                if (discountCardsList.containsKey(arr[1])) {
                    discountCard = discountCardsList.get(arr[1]);
                }
            } else if (!s.matches("\\d{1,13}-\\d{1,6}")) {
                throw new DiscountCardException("Input discount card data does not match template #d{1..10}");
            }
        }
        return discountCard;
    }

    private Map getItemsList(String[] userInputData) throws ArticleException {

        // basic map for receipt items
        items = new HashMap<>();
        // userRequestList uses for collect data from user as HashMap
        Map<String, String> userRequestList = new HashMap<>();

        // get user receipt item data
        for (String s : userInputData) {

            if (s.matches("\\d{1,13}-\\d{1,6}")) {
                String[] arr = s.split("-");
                String k = arr[0];
                String v = arr[1];

                // check for twins in user task, and if twins a present make sum qty of it
                if (userRequestList.containsKey(k)) {
                    v = String.valueOf(Integer.parseInt(userRequestList.get(k))
                            + Integer.parseInt(v));
                }
                userRequestList.put(k, v);
            } else if (!s.matches("#\\d{1,10}")) {
                throw new ArticleException("Input article data does not match template d{1..13}-d{1..6}");
            }
        }

        // looking for article in the price list and create an item of userReceipt
        userRequestList.forEach((k, v) -> {
            if (priceList.containsKey(k)) {
                Article article = priceList.get(k);
                Item item = null;
                try {
                    item = new Item(article.getCode(),
                            article.getName(),
                            article.getVatGroup(),
                            article.getPrice(),
                            article.getFlags(),
                            0,
                            Double.parseDouble(v));
                } catch (ArticleException e) {
                    e.printStackTrace();
                }
                items.put(k, item);
            }
        });
        return items;
    }

    @Override
    public void calcDiscount() {
        items.forEach((k, v) -> {
            // discount QTY
            if (((v.getFlags() & 1) == 1) && (v.getQuantity() > DISCOUNT_QTY_LIMIT)) {
                v.setDiscount(DISCOUNT_PRC_LIMIT);
            }
            // discount by card
            if ((((v.getFlags() >> 1) & 1) == 1) && (discountCard != null)) {
                v.setDiscount(v.getDiscount() + discountCard.getPercent());
            }

        });
    }

    @Override
    public void printReceipt(Boolean saveToFile) throws IOException {
        Printer printer = new Printer();
        printer.getHeader();
        items.forEach((k, v) -> printer.getItem(v, Adjustment.LEFT));
        if (discountCard != null) printer.getDiscount(discountCard);
        vats.forEach((k, v) -> printer.getVat(v, Adjustment.RIGHT));
        printer.print("Total discounts: " + String.format("%.2f", totalDiscount), Adjustment.RIGHT);
        printer.print("T O T A L: " + String.format("%.2f", total), Adjustment.RIGHT);
        printer.getFooter();
        printer.printout();
        if (saveToFile) {
            printer.printoutToFile(path);
        }
    }

    @Override
    public void calcVat() {
        items.forEach((k, v) -> {
            if (vats.containsKey(v.getVatGroup())) {
                Vat vat = vats.get(v.getVatGroup());
                double price = v.getPrice();
                double vatValue = vats.get(v.getVatGroup()).getValue();
                double vatItem = price - (price / (1 + vatValue / 100));
                vat.setVatGroupAmount(vat.getGroupAmount() + vatItem);
            } else throw new RuntimeException("Error vat");
        });
    }

    @Override
    public void getTotal() {
        items.forEach((k, v) -> {
            total += v.getItemTotal();
            totalDiscount += v.getItemTotal() * v.getDiscount() / 100;
        });
    }
}
