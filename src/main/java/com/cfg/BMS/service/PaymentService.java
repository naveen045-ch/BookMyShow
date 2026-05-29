package com.cfg.BMS.service;

import com.cfg.BMS.dto.PaymentDto;
import com.cfg.BMS.exception.ResourceNotFoundException;
import com.cfg.BMS.model.Payment;
import com.cfg.BMS.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;



    public PaymentDto createPayment(PaymentDto paymentDto) {

        Payment payment = new Payment();

        payment.setTransactionId(paymentDto.getTransactionId());
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentTime(paymentDto.getPaymentTime());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setStatus(paymentDto.getStatus());

        Payment savedPayment = paymentRepository.save(payment);

        return mapToPaymentDto(savedPayment);
    }


    public PaymentDto getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Payment not found"));

        if (payment == null) {
            return null;
        }

        return mapToPaymentDto(payment);
    }

    private PaymentDto mapToPaymentDto(Payment payment) {

        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setId(payment.getId());
        paymentDto.setTransactionId(payment.getTransactionId());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setPaymentTime(payment.getPaymentTime());
        paymentDto.setPaymentMethod(payment.getPaymentMethod());
        paymentDto.setStatus(payment.getStatus());

        return paymentDto;
    }
}
