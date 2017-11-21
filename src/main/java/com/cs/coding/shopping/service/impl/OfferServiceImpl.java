package com.cs.coding.shopping.service.impl;

import com.cs.coding.shopping.model.Item;
import com.cs.coding.shopping.offer.Offer;
import com.cs.coding.shopping.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public class OfferServiceImpl implements OfferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);

    private Map<Item, Offer> itemOfferMapping;

    public OfferServiceImpl(Map<Item,Offer> itemOfferMap) {
        this.itemOfferMapping = itemOfferMap;
    }

    public boolean isItemEligibleForOffer(Item item) {
        return itemOfferMapping.containsKey(item);
    }

    @Override
    public Optional<Offer> getOfferFor(Item item) {
        return isItemEligibleForOffer(item) ? Optional.of(itemOfferMapping.get(item))
                : Optional.empty();
    }

    public BigDecimal offerPrice(Item item, int currentBasketQuantity) {
        if (isItemEligibleForOffer(item)) {
            Offer itemUnderOffer = itemOfferMapping.get(item);
            LOGGER.info("Item Under Offer : {} and eligible Offer is: {}", item.getName(), itemUnderOffer.offerName());
            return itemUnderOffer.discountPrice(currentBasketQuantity, item.getPrice());

        }
        return BigDecimal.ZERO;
    }
}
