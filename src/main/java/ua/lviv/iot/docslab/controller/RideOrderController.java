package ua.lviv.iot.docslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.docslab.dto.RideOrderCsvDto;
import ua.lviv.iot.docslab.dto.RideOrderDto;
import ua.lviv.iot.docslab.model.Driver;
import ua.lviv.iot.docslab.model.RideOrder;
import ua.lviv.iot.docslab.service.DriverService;
import ua.lviv.iot.docslab.service.RideOrderService;
import ua.lviv.iot.docslab.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ride-order")
public class RideOrderController {

    private final RideOrderService rideOrderService;
    private final DriverService driverService;
    private final UserService userService;

    @GetMapping
    public String getAllRideOrdersPage (Model model) {
        model.addAttribute("rideOrdersList", rideOrderService.findAll());
        return "rideOrdersList";
    }

    @GetMapping("/{id}")
    public String getRideOrderPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("rideOrdersList", rideOrderService.findById(id));
        return "rideOrdersList";
    }

    @GetMapping("/add")
    public String createRideOrderPage(Model model){
        model.addAttribute("rideOrdersList", rideOrderService.findAll());
        model.addAttribute("driverList", driverService.findAll());
        model.addAttribute("userList", userService.findAll());
        return "rideOrderAdd";
    }

    @PostMapping
    public String saveRideOrderPage(RideOrderDto rideOrderDto){
        rideOrderService.save(rideOrderDto);
        return "redirect:/ride-order";
    }

    @GetMapping("/edit/{id}")
    public String editRideOrderPage(Model model, @PathVariable Long id){
        RideOrder rideOrder = rideOrderService.findById(id);
        model.addAttribute("rideOrder", rideOrder);
        model.addAttribute("driverList", driverService.findAll());
        model.addAttribute("userList", userService.findAll());
        return "rideOrderEdit";
    }

    @RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateRideOrder(RideOrderDto rideOrderDto, @PathVariable Long id){
        RideOrder rideOrder= rideOrderService.findById(id);
        Driver driver = driverService.findById(rideOrderDto.getDriverId());
        rideOrder.setDriver(driver);
        rideOrder.setUser(userService.findById(rideOrderDto.getUserId()));
        rideOrder.setPrice(Math.round(driver.getPricePerKilometer() * rideOrderDto.getDistance() * 100.0) / 100.0);
        rideOrder.setDistance(rideOrderDto.getDistance());
        rideOrderService.save(rideOrder);
        return "redirect:/ride-order";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteRideOrder(@PathVariable Long id){
        rideOrderService.delete(id);
        return "redirect:/ride-order";
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
