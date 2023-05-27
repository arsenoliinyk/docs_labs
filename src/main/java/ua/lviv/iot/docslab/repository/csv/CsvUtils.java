package ua.lviv.iot.docslab.repository.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import ua.lviv.iot.docslab.dto.RideOrderCsvDto;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    private static final String CSV_FILE_CONTENT_PATH = "./src/main/java/ua/lviv/iot/docslab/data/orders.csv";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT
            .builder()
            .setHeader("driverId", "userId", "distance", "createdAt")
            .setSkipHeaderRecord(true)
            .build();

    private CsvUtils() {
    }

    public static void writeRideOrdersToCsv(List<RideOrderCsvDto> orders) {
        File file = new File(CSV_FILE_CONTENT_PATH);
        try (Writer writer = new FileWriter(file);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSV_FORMAT)) {
            csvPrinter.printRecord("driverId", "userId", "distance", "createdAt");
            for (RideOrderCsvDto o : orders) {
                csvPrinter.printRecord(o.getDriverId(), o.getUserId(), o.getDistance(), o.getCreatedAt().format(DATE_TIME_FORMATTER));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<RideOrderCsvDto> readRideOrdersFromCsv() {
        List<RideOrderCsvDto> orders = new ArrayList<>();
        try (Reader reader = new FileReader(CSV_FILE_CONTENT_PATH);
             CSVParser csvParser = new CSVParser(reader, CSV_FORMAT)) {
            for (CSVRecord csvRecord : csvParser.getRecords()) {
                RideOrderCsvDto order = new RideOrderCsvDto(
                        Long.valueOf(csvRecord.get("driverId")),
                        Long.valueOf(csvRecord.get("userId")),
                        Double.valueOf(csvRecord.get("distance")),
                        LocalDateTime.parse(csvRecord.get("createdAt"), DATE_TIME_FORMATTER)
                );
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
