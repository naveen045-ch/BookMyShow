package com.cfg.BMS.service;

import com.cfg.BMS.dto.ScreenDto;
import com.cfg.BMS.dto.SeatDto;
import com.cfg.BMS.dto.TheaterDto;
import com.cfg.BMS.model.Screen;
import com.cfg.BMS.model.Seat;
import com.cfg.BMS.repository.ScreenRepository;
import com.cfg.BMS.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SeatService {

    @Autowired
    private  SeatRepository seatRepository;
    @Autowired
    private  ScreenRepository screenRepository;

    public SeatDto createSeat(SeatDto seatDto) {

        Screen screen = screenRepository.findById(
                        seatDto.getScreenDto().getId())
                .orElseThrow(() ->
                        new RuntimeException("Screen not found"));

        Seat seat = new Seat();

        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setSeatType(seatDto.getSeatType());
        seat.setBasePrice(seatDto.getBasePrice());
        seat.setScreen(screen);

        Seat savedSeat = seatRepository.save(seat);

        return mapToSeatDto(savedSeat);
    }


    public SeatDto getSeatById(Long id) {

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Seat not found"));

        return mapToSeatDto(seat);
    }


    public List<SeatDto> getAllSeats() {

        return seatRepository.findAll()
                .stream()
                .map(this::mapToSeatDto)
                .toList();
    }


    public SeatDto updateSeat(Long id, SeatDto seatDto) {

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Seat not found"));

        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setSeatType(seatDto.getSeatType());
        seat.setBasePrice(seatDto.getBasePrice());

        if (seatDto.getScreenDto() != null) {

            Screen screen = screenRepository.findById(
                            seatDto.getScreenDto().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Screen not found"));

            seat.setScreen(screen);
        }

        Seat updatedSeat = seatRepository.save(seat);

        return mapToSeatDto(updatedSeat);
    }


    public void deleteSeat(Long id) {

        if (!seatRepository.existsById(id)) {
            throw new RuntimeException("Seat not found");
        }

        seatRepository.deleteById(id);
    }

    private SeatDto mapToSeatDto(Seat seat) {

        SeatDto dto = new SeatDto();

        dto.setId(seat.getId());
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setSeatType(seat.getSeatType());
        dto.setBasePrice(seat.getBasePrice());



        ScreenDto screenDto = new ScreenDto();
        screenDto.setId(seat.getScreen().getId());
        screenDto.setTotalSeats(seat.getScreen().getTotalSeats());
        screenDto.setName(seat.getScreen().getName());

        TheaterDto  theaterDto = new TheaterDto();
        theaterDto.setId(seat.getScreen().getTheater().getId());
        theaterDto.setName(seat.getScreen().getTheater().getName());
        theaterDto.setCity(seat.getScreen().getTheater().getCity());
        theaterDto.setTotalScreens(seat.getScreen().getTheater().getTotalScreens());
        theaterDto.setAddress(seat.getScreen().getTheater().getAddress());

        screenDto.setTheater(theaterDto);

        dto.setScreenDto(screenDto);


        return dto;
    }
}