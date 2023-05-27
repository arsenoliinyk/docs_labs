package ua.lviv.iot.docslab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.lviv.iot.docslab.enums.PaymentMethod;

@Data
@AllArgsConstructor
public class PaymentDto {
    private Long rideOrderId;
    private PaymentMethod paymentMethod;
    private Long parentPaymentId;
}