package com.cs.coding.shopping.offer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public class BuyOneGetOneOffer implements Offer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyOneGetOneOffer.class);

    private static final int ELIGIBLE_QUANTITIES = 2;

    @Override
    public int eligibleQuantitiesForOffer() {
        LOGGER.info("Eligible quantities for BuyOneGetOneOffer is: {}", ELIGIBLE_QUANTITIES);
        return ELIGIBLE_QUANTITIES;
    }

    @Override
    public String offerName() {
        return "Buy One Get One Free";
    }
}
