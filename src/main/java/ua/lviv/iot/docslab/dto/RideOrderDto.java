package ua.lviv.iot.docslab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RideOrderDto {
    private Long driverId;
    private Long userId;
    private Double distance;
}
