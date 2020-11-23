package process;

import exceptions.ArticleException;
import exceptions.DiscountCardException;

import exceptions.VatException;
import io.DataFileReader;
import models.discountCard.DiscountCard;
import models.priceList.Article;
import models.vat.Vat;
import utils.ArticleParser;
import utils.DiscountCardParser;
import utils.VatParser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class uses as main handler of UserReceipt actions
 */
public class TaskHandler implements ReceiptCreator {
    private String[] inputData;

    private final Path PRICE_LIST_PATH = Paths.get("src/main/resources/priceList.txt");
    private final Path DISCOUNT_CARDS_PATH = Paths.get("src/main/resources/discountCards.txt");
    private final Path VAT_PATH = Paths.get("src/main/resources/vat.txt");
    private final Path RECEIPT_PATH = Paths.get("src/main/resources/receipt.txt");

    public TaskHandler(String[] args) {
        this.inputData = args;
    }

    /**
     * This method collect data from the different data base files
     * and create main UserReceipt object
     * After construction the method invoke userReceipt internal methods for
     * create, calc disc, calc vats , printout data.
     * At the end we call printout with param true - means save data tot the file.
     *
     * @throws IOException
     * @throws DiscountCardException
     * @throws ArticleException
     * @throws VatException
     */
    @Override
    public void create() throws IOException, DiscountCardException, ArticleException, VatException {

        //load priceList list from file
        List<String> rawPriceList = new DataFileReader().readReceiptFromFile(PRICE_LIST_PATH);
        Map<String, Article> priceList = new ArticleParser().getPriceList(rawPriceList);

        //load discount cards list
        List<String> rawDiscountCardList = new DataFileReader().readReceiptFromFile(DISCOUNT_CARDS_PATH);
        Map<String, DiscountCard> discountCards = new DiscountCardParser().getDiscountCardMap(rawDiscountCardList);

        //load vat list
        List<String> rawVatList = new DataFileReader().readReceiptFromFile(VAT_PATH);
        Map<String, Vat> vats = new VatParser().getVatMap(rawVatList);

        //load user receipt data
        UserReceipt userReceipt = new UserReceipt(inputData, priceList, discountCards, vats, RECEIPT_PATH);
        userReceipt.create();
        userReceipt.calcDiscount();
        userReceipt.calcVat();
        userReceipt.getTotal();
        userReceipt.printReceipt(true);
    }
}
