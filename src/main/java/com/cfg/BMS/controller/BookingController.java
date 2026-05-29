package com.cfg.BMS.controller;

import com.cfg.BMS.dto.BookingDto;
import com.cfg.BMS.dto.BookingRequestDto;
import com.cfg.BMS.repository.BookingRepository;
import com.cfg.BMS.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;



    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto){
        return new ResponseEntity<>(bookingService.createBooking(bookingRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@Valid @PathVariable Long id){
        return  ResponseEntity.ok(bookingService.getBookingById(id));
    }



}
