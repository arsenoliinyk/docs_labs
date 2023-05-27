package ua.lviv.iot.docslab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`ride_order`")
public class RideOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("orders")
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "price")
    private Double price;

    public RideOrder(Driver driver, User user, Double distance, LocalDateTime createdAt) {
        this.driver = driver;
        this.user = user;
        this.distance = distance;
        this.price = Math.round(driver.getPricePerKilometer() * distance * 100.0) / 100.0;
        this.createdAt = createdAt;
    }

    public RideOrder(Driver driver, User user, Double distance) {
        this.driver = driver;
        this.user = user;
        this.distance = distance;
        this.price = Math.round(driver.getPricePerKilometer() * distance * 100.0) / 100.0;
        this.createdAt = LocalDateTime.now();
    }

}