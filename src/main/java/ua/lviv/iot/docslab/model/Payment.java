package ua.lviv.iot.docslab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.lviv.iot.docslab.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cost")
    private Double cost;

    @OneToOne
    @JoinColumn(name = "order_id")
    private RideOrder rideOrder;

    @OneToOne
    @JoinColumn(name = "serder_id")
    private User user_id;

    @OneToOne
    @JoinColumn(name = "recipient_id")
    private Driver driver_id;

    @Column(name = "transaction_timestamp")
    private LocalDateTime transactionTimestamp;

    @Column(name = "payment_type")
    private PaymentMethod paymentMethod;

    public Payment(RideOrder rideOrder, PaymentMethod paymentMethod) {
        this.rideOrder = rideOrder;
        this.cost = rideOrder.getPrice();
        this.user_id = rideOrder.getUser();
        this.driver_id = rideOrder.getDriver();
        this.transactionTimestamp = LocalDateTime.now();
        this.paymentMethod = paymentMethod;
    }
}
