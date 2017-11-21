package com.cs.coding.shopping.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public enum  Item {

    APPLE("Apple", BigDecimal.valueOf(0.35)),
    BANANA("Banana", BigDecimal.valueOf(0.20)),
    MELON("Melon", BigDecimal.valueOf(0.50)),
    LIME("Lime", BigDecimal.valueOf(0.15)),
    UNRECOGNIZED("UnrecognizedItem", BigDecimal.ZERO);

    private String name;
    private BigDecimal price;

    private Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private static Map<String, Item> CACHED_ITEMS = new HashMap<>();

    static {
        for(Item item: Item.values()) {
            CACHED_ITEMS.put(item.getName(), item);
        }
    }

    public static Item of(String itemName) {
        return (CACHED_ITEMS.containsKey(itemName)) ? CACHED_ITEMS.get(itemName) : Item.UNRECOGNIZED;
    }
}
