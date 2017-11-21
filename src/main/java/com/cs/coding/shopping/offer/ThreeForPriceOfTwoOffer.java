package com.cs.coding.shopping.offer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public class ThreeForPriceOfTwoOffer implements Offer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreeForPriceOfTwoOffer.class);

    private static final int ELIGIBLE_QUANTITIES = 3;

    @Override
    public int eligibleQuantitiesForOffer() {
        LOGGER.info("Eligible quantities for ThreeForPriceOfTwoOffer is: {}", ELIGIBLE_QUANTITIES);
        return ELIGIBLE_QUANTITIES;
    }

    @Override
    public String offerName() {
        return "Buy 3 for price of 2";
    }
}
