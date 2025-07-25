package org.example.payment.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Order {

    private long id;
    private String product;
    private int quantity;
    private boolean paid;

}

