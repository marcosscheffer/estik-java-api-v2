package com.marcos.estik.stock.adapter.out.persistence;

import java.util.UUID;

public interface CategoryTotal {
    UUID getCategoryId();
    String getName();
    Long getTotal();
    
}
