package ua.lviv.iot.docslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.docslab.model.Driver;
import ua.lviv.iot.docslab.repository.DriverDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverDao driverDao;

    public List<Driver> findAll(){
        return driverDao.findAll();
    }


    public Driver findById(Long id) {
        return driverDao.findById(id).orElseThrow(() -> new RuntimeException("DriverResponse not found"));
    }
}