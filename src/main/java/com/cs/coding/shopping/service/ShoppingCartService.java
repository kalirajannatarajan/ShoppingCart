package com.cs.coding.shopping.service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wingrowsoft on 18/11/2017.
 */
public interface ShoppingCartService {
    BigDecimal priceOfShoppingBasket(List<String> basketItems);
    List<String> getItemizedBill();
}
