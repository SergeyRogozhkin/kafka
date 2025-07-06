package org.example.shipping.model;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderNotify {
    private LocalDate shippingDate;
    private String deliveryAddress;
}
