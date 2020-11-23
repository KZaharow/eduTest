import exceptions.ArticleException;
import exceptions.DiscountCardException;
import exceptions.VatException;
import models.discountCard.DiscountCard;
import models.priceList.Article;
import models.receipt.Item;
import models.vat.Vat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import process.UserReceipt;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class ItemTest {

    private Map<String, Item> items;

    @Before
    public void setUp() throws ArticleException {
        Item item1 = new Item("123", "Tomat", "A", 100, 255, 10, 2.5);
        Item item2 = new Item("456", "Orange", "B", 3, 255, 10, 2);
        Item item3 = new Item("789", "Apple", "C", 10, 255, 10, 5);
        items = new HashMap<>();
        items.put("123", item1);
        items.put("456", item2);
        items.put("789", item3);
    }

    @Test
    public void loadPriceList() {
        Assert.assertEquals(250, items.get("123").getItemTotal(), 0);
        Assert.assertEquals(6, items.get("456").getItemTotal(), 0);
        Assert.assertEquals(50, items.get("789").getItemTotal(), 0);
    }


    @Test(expected = VatException.class)
    public void checkUserReceiptVatException() throws VatException {
        Map<String, Vat> vats = new HashMap<>();
        // wrong value
        vats.put("A", new Vat("A", -20, 0));
    }

    @Test(expected = DiscountCardException.class)
    public void checkUserReceiptDiscountCardException() throws DiscountCardException {
        Map<String, DiscountCard> discountCards = new HashMap<>();
        // wrong value
        discountCards.put("123", new DiscountCard("123", "test card", 110));
    }

    @Test(expected = ArticleException.class)
    public void checkUserReceiptArticleException() throws ArticleException {
        Map<String, Article> pricelist = new HashMap<>();
        // wrong value
        pricelist.put("123", new Article("123", "test", "A", 0, 255));
    }

    @Test(expected = ArticleException.class)
    public void checkUserReceiptEmptyArticleException() throws ArticleException, VatException, DiscountCardException {
        Map<String, Vat> vats = new HashMap<>();
        Map<String, DiscountCard> discountCards = new HashMap<>();
        Map<String, Article> pricelist = new HashMap<>();

        vats.put("A", new Vat("A", 20, 0));
        discountCards.put("123", new DiscountCard("123", "test card", 10));
        pricelist.put("123", new Article("123", "", "A", 0, 255));

        String[] inputString = {"1-1", "2-2"};
        Path path = Paths.get("test.txt");

        UserReceipt userReceipt = new UserReceipt(inputString, pricelist, discountCards, vats, path);
        userReceipt.create();

    }




}
