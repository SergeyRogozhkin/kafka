package org.example.shipping.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private long id;
    private String product;
    private int quantity;
    private boolean paid;

}
