package com.cs.coding.shopping.service;

import com.cs.coding.shopping.model.Item;
import com.cs.coding.shopping.offer.Offer;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public interface OfferService {

    boolean isItemEligibleForOffer(Item item);

    Optional<Offer> getOfferFor(Item item);

    BigDecimal offerPrice(Item item, int currentBasketQuantity);

}
