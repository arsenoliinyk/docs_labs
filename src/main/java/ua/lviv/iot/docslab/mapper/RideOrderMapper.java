package ua.lviv.iot.docslab.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.lviv.iot.docslab.dto.RideOrderCsvDto;
import ua.lviv.iot.docslab.dto.RideOrderDto;
import ua.lviv.iot.docslab.model.RideOrder;
import ua.lviv.iot.docslab.service.DriverService;
import ua.lviv.iot.docslab.service.UserService;

@Component
@RequiredArgsConstructor
public class RideOrderMapper {

    private final UserService userService;
    private final DriverService driverService;


    public RideOrder map(RideOrderCsvDto rideOrderCsvDto) {
        return new RideOrder(
                driverService.findById(rideOrderCsvDto.getDriverId()),
                userService.findById(rideOrderCsvDto.getUserId()),
                rideOrderCsvDto.getDistance(),
                rideOrderCsvDto.getCreatedAt()
        );
    }

    public RideOrder map(RideOrderDto rideOrderDto) {
        return new RideOrder(
                driverService.findById(rideOrderDto.getDriverId()),
                userService.findById(rideOrderDto.getUserId()),
                rideOrderDto.getDistance()
        );
    }
}
