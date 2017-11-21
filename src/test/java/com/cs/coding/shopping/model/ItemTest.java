package com.cs.coding.shopping.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testEligibleItems() {

        Item apple = Item.of("Apple");
        assertTrue( Item.APPLE == apple );

        Item banana = Item.of("Banana");
        assertTrue(Item.BANANA == banana);

        Item melon = Item.of("Melon");
        assertTrue(Item.MELON == melon);

        Item lime = Item.of("Lime");
        assertTrue(Item.LIME == lime);
    }

    @Test
    public void testNonEligibleItem() {
        Item unrecognized = Item.of("Something");
        assertTrue( unrecognized == Item.UNRECOGNIZED);
    }

}
