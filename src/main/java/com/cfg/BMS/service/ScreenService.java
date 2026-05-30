package com.cfg.BMS.service;

import com.cfg.BMS.dto.ScreenDto;
import com.cfg.BMS.dto.TheaterDto;
import com.cfg.BMS.model.Screen;
import com.cfg.BMS.model.Theater;
import com.cfg.BMS.repository.ScreenRepository;
import com.cfg.BMS.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheaterRepository theaterRepository;


    public ScreenDto createScreen(ScreenDto screenDto) {

        Theater theater = theaterRepository.findById(
                        screenDto.getTheater().getId())
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        Screen screen = new Screen();
        screen.setName(screenDto.getName());
        screen.setTotalSeats(screenDto.getTotalSeats());
        screen.setTheater(theater);

        Screen savedScreen = screenRepository.save(screen);

        return mapToScreenDto(savedScreen);
    }

    public ScreenDto getScreenById(Long id) {

        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        return mapToScreenDto(screen);
    }


    public List<ScreenDto> getAllScreens() {

        return screenRepository.findAll()
                .stream()
                .map(this::mapToScreenDto)
                .toList();
    }


    public ScreenDto updateScreen(Long id, ScreenDto screenDto) {

        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        screen.setName(screenDto.getName());
        screen.setTotalSeats(screenDto.getTotalSeats());

        if (screenDto.getTheater() != null) {

            Theater theater = theaterRepository.findById(
                            screenDto.getTheater().getId())
                    .orElseThrow(() -> new RuntimeException("Theater not found"));

            screen.setTheater(theater);
        }

        Screen updatedScreen = screenRepository.save(screen);

        return mapToScreenDto(updatedScreen);
    }


    public void deleteScreen(Long id) {

        if (!screenRepository.existsById(id)) {
            throw new RuntimeException("Screen not found");
        }

        screenRepository.deleteById(id);
    }

    private ScreenDto mapToScreenDto(Screen screen) {

        ScreenDto screenDto = new ScreenDto();

        screenDto.setId(screen.getId());
        screenDto.setName(screen.getName());
        screenDto.setTotalSeats(screen.getTotalSeats());



        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(screen.getTheater().getId());
        theaterDto.setName(screen.getTheater().getName());
        theaterDto.setTotalScreens(screen.getTheater().getTotalScreens());
        theaterDto.setCity(screen.getTheater().getCity());
        theaterDto.setAddress(screen.getTheater().getAddress());


        screenDto.setTheater(theaterDto);


        return screenDto;
    }
}
