package com.cs.coding.shopping;

import com.cs.coding.shopping.model.Item;
import com.cs.coding.shopping.offer.BuyOneGetOneOffer;
import com.cs.coding.shopping.offer.Offer;
import com.cs.coding.shopping.offer.ThreeForPriceOfTwoOffer;
import com.cs.coding.shopping.service.OfferService;
import com.cs.coding.shopping.service.impl.OfferServiceImpl;
import com.cs.coding.shopping.service.impl.ShoppingCartServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public class ShoppingCartApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartApp.class);

    public static void main(String args[]) {

        if (args.length < 1) {
            LOGGER.error("Usage: ShoppingCartApp <Shopping basket items>");
            LOGGER.error("Usage: ShoppingCartApp Apple Apple Banana");
            System.exit(-1);
        }

        List<String> shoppingItems =  Arrays.asList(args);

        Map<Item, Offer> itemOfferMap = new HashMap<>();
        itemOfferMap.put(Item.MELON, new BuyOneGetOneOffer());
        itemOfferMap.put(Item.LIME, new ThreeForPriceOfTwoOffer());

        OfferService offerService = new OfferServiceImpl(itemOfferMap);

        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl(offerService);

        BigDecimal total = shoppingCartService.priceOfShoppingBasket(shoppingItems);

        for (String itemizedEntry : shoppingCartService.getItemizedBill()) {
            LOGGER.info(itemizedEntry);
        }

        LOGGER.info("Total : {}", total.toPlainString() );


    }

}
