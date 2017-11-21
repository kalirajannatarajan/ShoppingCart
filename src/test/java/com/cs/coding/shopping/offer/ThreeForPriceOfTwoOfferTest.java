package com.cs.coding.shopping.offer;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ThreeForPriceOfTwoOfferTest {

    private Offer offerUnderTest;

    @Before
    public void setup() {
        offerUnderTest = new ThreeForPriceOfTwoOffer();
    }

    @Test
    public void eligible_offertotal_of_one() {

        BigDecimal discountedPrice = offerUnderTest.discountPrice(3, BigDecimal.valueOf(0.5));

        assertEquals(BigDecimal.valueOf(0.5).negate(), discountedPrice);
    }

    @Test
    public void eligible_offertotal_more_than_one() {

        BigDecimal discountedPrice = offerUnderTest.discountPrice(30, BigDecimal.valueOf(0.5));

        assertEquals(BigDecimal.valueOf(5.0).negate(), discountedPrice);
    }

    @Test
    public void ineligible_quantities() {
        BigDecimal discountedPrice = offerUnderTest.discountPrice(2, BigDecimal.valueOf(0.5));
        assertEquals(BigDecimal.ZERO, discountedPrice);

        BigDecimal discountedPrice2 = offerUnderTest.discountPrice(0, BigDecimal.valueOf(0.5));
        assertEquals(BigDecimal.ZERO, discountedPrice2);


        BigDecimal discountedPrice3 = offerUnderTest.discountPrice(-1, BigDecimal.valueOf(0.5));
        assertEquals(BigDecimal.ZERO, discountedPrice3);

    }

    @Test
    public void input_quantities_not_exactly_divisible_by_eligible_quantities() {
        BigDecimal discountedPrice = offerUnderTest.discountPrice(5, BigDecimal.valueOf(0.5));
        assertEquals(BigDecimal.valueOf(0.5).negate(), discountedPrice);
    }
}
