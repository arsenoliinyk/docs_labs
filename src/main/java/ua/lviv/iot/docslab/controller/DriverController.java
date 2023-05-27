package ua.lviv.iot.docslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.docslab.model.Driver;
import ua.lviv.iot.docslab.service.DriverService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    @GetMapping
    public String getAllDriversPage(Model model){
        model.addAttribute("driversList", driverService.findAll());
        return "driversList";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Driver> getDriver(@PathVariable("id") Long id){
        return ResponseEntity.ok(driverService.findById(id));
    }

}
