package ua.lviv.iot.docslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.docslab.dto.RideOrderCsvDto;
import ua.lviv.iot.docslab.dto.RideOrderDto;
import ua.lviv.iot.docslab.model.RideOrder;
import ua.lviv.iot.docslab.service.RideOrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ride_order")
public class RideOrderController {

    private final RideOrderService rideOrderService;

    @GetMapping
    public ResponseEntity<List<RideOrder>> getAllRideOrders(){
        return ResponseEntity.ok(rideOrderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideOrder> getRideOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(rideOrderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RideOrder> createRideOrder(@RequestBody RideOrderDto rideOrderDto){
        return ResponseEntity.ok(rideOrderService.create(rideOrderDto));
    }

    @GetMapping("/generate-csv")
    public ResponseEntity<List<RideOrderCsvDto>> createRideOrdersCsv() {
        return ResponseEntity.ok(rideOrderService.generateRideOrdersCsv());
    }

    @GetMapping("/save-csv-to-db")
    public ResponseEntity<List<RideOrder>> saveCsvToDb() {
        return ResponseEntity.ok(rideOrderService.saveCsvToDb());
    }

}
