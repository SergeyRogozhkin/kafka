package org.example.orders.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
public class Order {

    private long id;
    private String product;
    private int quantity;

    @Override
    public String toString() {
        return "Ордер [id=" + id + ", " +
                "продукт=" + product + ", " +
                "количество=" + quantity + "]";
    }
}
