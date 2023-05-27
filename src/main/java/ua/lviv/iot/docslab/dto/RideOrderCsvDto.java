package ua.lviv.iot.docslab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideOrderCsvDto {
    private Long driverId;
    private Long userId;
    private Double distance;
    private LocalDateTime createdAt;
}