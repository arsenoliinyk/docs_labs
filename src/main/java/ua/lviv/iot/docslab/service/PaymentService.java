package ua.lviv.iot.docslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lviv.iot.docslab.dto.PaymentDto;
import ua.lviv.iot.docslab.model.Payment;
import ua.lviv.iot.docslab.model.RideOrder;
import ua.lviv.iot.docslab.repository.PaymentDao;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentDao paymentDao;
    private final RideOrderService rideOrderService;

    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    public Payment findById(Long id) {
        return paymentDao.findById(id).orElseThrow(() -> new RuntimeException("PaymentResponse not found"));
    }

    public Payment create(PaymentDto paymentDto) {
        Payment payment = new Payment(
                rideOrderService.findById(paymentDto.getRideOrderId()),
                paymentDto.getPaymentMethod()
        );
        return paymentDao.save(payment);
    }

}
