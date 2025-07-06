package org.example.notifications.model;

import lombok.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderNotify {
    private LocalDate shippingDate;
    private String deliveryAddress;
}

