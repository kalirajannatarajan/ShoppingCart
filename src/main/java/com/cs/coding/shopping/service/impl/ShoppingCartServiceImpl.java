package com.cs.coding.shopping.service.impl;

import com.cs.coding.shopping.model.Item;
import com.cs.coding.shopping.service.OfferService;
import com.cs.coding.shopping.service.ShoppingCartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private OfferService offerService;
    private Map<Item,AtomicInteger> itemQuantityMap;
    List<String> itemizedBill;

    public ShoppingCartServiceImpl(OfferService offerService) {
        this.offerService = offerService;
        itemQuantityMap = new HashMap<>();
        itemizedBill = new ArrayList<>();

    }

    public BigDecimal priceOfShoppingBasket(List<String> basketItems) {
        itemizedBill = new ArrayList<>(basketItems.size() + 10);
         basketItems.stream().map((basketItem) -> Item.of(basketItem)).forEach(
                (item) -> {
                    AtomicInteger countPresentAlready = itemQuantityMap.putIfAbsent(item,new AtomicInteger(1));
                   if (countPresentAlready != null) {
                       countPresentAlready.incrementAndGet();
                   }
                   addToItemizedBill(itemizedBill, item);
                }
        );

        BigDecimal subTotal = getSubTotalWithOutOffers(itemQuantityMap);

        BigDecimal offerTotal = getOfferDiscount(itemQuantityMap);

        addOffersToItemizedBill(itemizedBill, itemQuantityMap);

        return subTotal.add(offerTotal) ;
    }

    protected Map<Item, AtomicInteger> getItemQuantityMap() {
        return itemQuantityMap;
    }

    public List<String> getItemizedBill() {
        return itemizedBill;
    }

    protected BigDecimal getSubTotalWithOutOffers(Map<Item,AtomicInteger> itemCountMap) {
      return itemCountMap.keySet()
              .stream()
              .map( (item) -> item.getPrice().multiply(BigDecimal.valueOf(itemCountMap.get(item).intValue())))
              .reduce(BigDecimal.ZERO, BigDecimal::add);
     }

    protected BigDecimal getOfferDiscount(Map<Item,AtomicInteger> itemCountMap) {
       return itemCountMap.keySet().stream().filter( (item) -> offerService.isItemEligibleForOffer(item))
                .map((item) -> offerService.offerPrice(item, itemCountMap.get(item).intValue()))
               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected void addOffersToItemizedBill(List<String> itemizedBill, Map<Item,AtomicInteger> itemCountMap) {
        itemCountMap.keySet().stream().filter( (item) -> offerService.isItemEligibleForOffer(item)).forEach(
                (item) -> {
                    String offerName = offerService.getOfferFor(item).get().offerName();
                    BigDecimal offerDiscount = offerService.offerPrice(item, itemCountMap.get(item).intValue());
                    StringBuilder sb = new StringBuilder();
                    sb.append(offerName).append("(").append(item.getName()).append(")").append("\t").append(offerDiscount.toPlainString());
                    itemizedBill.add(sb.toString());
                }
        );
    }

    protected void addToItemizedBill(List<String> itemizedBill, Item item) {
        StringBuilder itemizedEntry = new StringBuilder();
        itemizedEntry.append(item.getName()).append("\t").append(item.getPrice().toPlainString());
        itemizedBill.add(itemizedEntry.toString());
    }
}
