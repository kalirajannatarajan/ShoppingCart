package com.cs.coding.shopping.service.impl;

import com.cs.coding.shopping.model.Item;
import com.cs.coding.shopping.offer.Offer;
import com.cs.coding.shopping.service.OfferService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

    private OfferService serviceUnderTest;

    @Mock
    private Map<Item, Offer> mockMap;

    @Mock
    private Offer mockOffer;

    @Before
    public void setup() {
        serviceUnderTest = new OfferServiceImpl(mockMap);
    }

    @Test
    public void offer_eligibility() {
        when(mockMap.containsKey(Item.APPLE)).thenReturn(true);
        assertTrue( serviceUnderTest.isItemEligibleForOffer(Item.APPLE));

        when(mockMap.containsKey(Item.BANANA)).thenReturn(false);
        assertFalse( serviceUnderTest.isItemEligibleForOffer(Item.BANANA));
    }

    @Test
    public void offerPrice_for_eligibleItems_with_eligibleQuantities() {
        when(mockMap.containsKey(Item.APPLE)).thenReturn(true);
        when(mockMap.get(Item.APPLE)).thenReturn(mockOffer);
        when(mockOffer.discountPrice(10, Item.APPLE.getPrice())).thenReturn(BigDecimal.valueOf(1.75).negate());
        BigDecimal discountValue = serviceUnderTest.offerPrice(Item.APPLE, 10);

        assertEquals(BigDecimal.valueOf(1.75).negate(), discountValue);
    }

    @Test
    public void offerPrice_for_inEligibleItems() {
        when(mockMap.containsKey(Item.APPLE)).thenReturn(false);
        BigDecimal discountValue = serviceUnderTest.offerPrice(Item.APPLE, 10);

        assertEquals(BigDecimal.ZERO, discountValue);
    }
}
