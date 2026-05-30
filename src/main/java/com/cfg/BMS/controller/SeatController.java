package com.cfg.BMS.controller;

import com.cfg.BMS.dto.SeatDto;
import com.cfg.BMS.model.Seat;
import com.cfg.BMS.repository.SeatRepository;
import com.cfg.BMS.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<SeatDto> createSeat(
            @RequestBody SeatDto seatDto) {

        return new ResponseEntity<>(seatService.createSeat(seatDto), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable Long id) {

        return ResponseEntity.ok(seatService.getSeatById(id));
    }
    @GetMapping
    public ResponseEntity<List<SeatDto>> getAllSeat() {

        return ResponseEntity.ok(seatService.getAllSeats());
    }
}
