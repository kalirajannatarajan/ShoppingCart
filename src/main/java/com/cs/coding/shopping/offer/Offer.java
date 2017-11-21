package com.cs.coding.shopping.offer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public interface Offer {

    Logger LOGGER = LoggerFactory.getLogger(Offer.class);

   int eligibleQuantitiesForOffer();

   String offerName();

   default BigDecimal discountPrice(int quantities, BigDecimal itemPrice) {
       int offerEligibilityTotal = quantities / eligibleQuantitiesForOffer();
       LOGGER.info("Total Eligible Items for offer : {}", offerEligibilityTotal);
       return (offerEligibilityTotal > 0) ? itemPrice.multiply(BigDecimal.valueOf(offerEligibilityTotal)).negate() : BigDecimal.ZERO;
   }
}
