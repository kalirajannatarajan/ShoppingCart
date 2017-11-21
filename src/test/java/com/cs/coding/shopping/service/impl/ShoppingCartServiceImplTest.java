package com.cs.coding.shopping.service.impl;


import com.cs.coding.shopping.model.Item;
import com.cs.coding.shopping.offer.BuyOneGetOneOffer;
import com.cs.coding.shopping.offer.Offer;
import com.cs.coding.shopping.offer.ThreeForPriceOfTwoOffer;
import com.cs.coding.shopping.service.OfferService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ShoppingCartServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImplTest.class);

    @Test
    public void test_integration_buyonegetone() {
        Map<Item, Offer> itemOfferMap = new HashMap<>();
        itemOfferMap.put(Item.MELON, new BuyOneGetOneOffer());

        OfferService offerService = new OfferServiceImpl(itemOfferMap);

        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl(offerService);

        BigDecimal cartTotal = shoppingCartService.priceOfShoppingBasket(Arrays.asList("Melon","Melon","Melon","Melon"));

        assertTrue( BigDecimal.valueOf(1.0).compareTo(cartTotal) == 0);

        LOGGER.info("ItemQuantityMap: {}", shoppingCartService.getItemQuantityMap());
        LOGGER.info("Itemized Bill : \n {}", shoppingCartService.getItemizedBill());
        LOGGER.info("Cart Total : {}", cartTotal);
    }

    @Test
    public void test_integration_threeforpriceoftwo() {
        Map<Item, Offer> itemOfferMap = new HashMap<>();
        itemOfferMap.put(Item.LIME, new ThreeForPriceOfTwoOffer());

        OfferService offerService = new OfferServiceImpl(itemOfferMap);

        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl(offerService);

        BigDecimal cartTotal = shoppingCartService.priceOfShoppingBasket(Arrays.asList("Lime","Lime","Lime","Lime","Lime","Lime"));

        assertTrue(BigDecimal.valueOf(0.6).compareTo(cartTotal) == 0);

        LOGGER.info("ItemQuantityMap: {}", shoppingCartService.getItemQuantityMap());
        LOGGER.info("Itemized Bill : \n {}", shoppingCartService.getItemizedBill());
        LOGGER.info("Cart Total : {}", cartTotal);
    }

    @Test
    public void test_integration_nooffer() {
        Map<Item, Offer> itemOfferMap = new HashMap<>();
        OfferService offerService = new OfferServiceImpl(itemOfferMap);

        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl(offerService);

        BigDecimal cartTotal = shoppingCartService.priceOfShoppingBasket(Arrays.asList("Apple","Apple","Apple","Apple","Apple","Apple"));

        assertTrue(BigDecimal.valueOf(2.1).compareTo(cartTotal) == 0);

    }

    @Test
    public void test_all_offers_along_with_item_under_no_offer() {
        Map<Item, Offer> itemOfferMap = new HashMap<>();
        itemOfferMap.put(Item.MELON, new BuyOneGetOneOffer());
        itemOfferMap.put(Item.LIME, new ThreeForPriceOfTwoOffer());

        OfferService offerService = new OfferServiceImpl(itemOfferMap);

        ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl(offerService);

        BigDecimal cartTotal = shoppingCartService.priceOfShoppingBasket(Arrays.asList("Apple","Apple","Melon","Melon","Lime","Lime","Lime","Banana", "Banana"));

        LOGGER.info("Itemized Bill : \n {}", shoppingCartService.getItemizedBill());

        assertTrue(BigDecimal.valueOf(1.9).compareTo(cartTotal) == 0);
    }

}
