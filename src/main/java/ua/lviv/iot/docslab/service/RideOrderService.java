package ua.lviv.iot.docslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.docslab.dto.RideOrderCsvDto;
import ua.lviv.iot.docslab.dto.RideOrderDto;
import ua.lviv.iot.docslab.mapper.RideOrderMapper;
import ua.lviv.iot.docslab.model.RideOrder;
import ua.lviv.iot.docslab.repository.RideOrderDao;
import ua.lviv.iot.docslab.repository.csv.CsvUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideOrderService {

    private final RideOrderDao rideOrderDao;
    private final RideOrderMapper rideOrderMapper;
    private final Random random = new Random();

    public List<RideOrder> findAll() {
        return rideOrderDao.findAll();
    }

    public RideOrder findById(Long id) {
        return rideOrderDao.findById(id).orElseThrow(() -> new RuntimeException("RideOrderResponse not found"));
    }

    public RideOrder create(RideOrderDto rideOrderDto) {
        return rideOrderDao.save(rideOrderMapper.map(rideOrderDto));
    }

    public void save(RideOrderDto rideOrderDto) {
        rideOrderDao.save(rideOrderMapper.map(rideOrderDto));
    }

    public void save(RideOrder rideOrder) {
        rideOrderDao.save(rideOrder);
    }

    public void delete(Long id){
        rideOrderDao.deleteById(id);
    }

    public List<RideOrderCsvDto> generateRideOrdersCsv() {
        List<RideOrderCsvDto> rideOrders = generateRideOrdersCsvDtos(1000);
        CsvUtils.writeRideOrdersToCsv(rideOrders);
        return null;
    }

    public List<RideOrderCsvDto> generateRideOrdersCsvDtos(int num) {
        List<RideOrderCsvDto> rideOrders = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        for (int i = 0; i < num; i++) {
            rideOrders.add(new RideOrderCsvDto(
                    random.nextLong(5) + 1,
                    random.nextLong(5) + 1,
                    Double.parseDouble(df.format(random.nextDouble(0,10) + 1)),
                    LocalDateTime.now()));
        }
        return rideOrders;
    }

    public List<RideOrder> saveCsvToDb() {
        final List<RideOrder> rideOrders = CsvUtils.readRideOrdersFromCsv()
                .stream()
                .map(rideOrderMapper::map)
                .toList();
        return rideOrderDao.saveAll(rideOrders);
    }



}
