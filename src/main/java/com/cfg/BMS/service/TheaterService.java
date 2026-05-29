package com.cfg.BMS.service;

import com.cfg.BMS.dto.TheaterDto;
import com.cfg.BMS.exception.ResourceNotFoundException;
import com.cfg.BMS.model.Theater;
import com.cfg.BMS.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public TheaterDto createTheater(TheaterDto theaterDto) {
        Theater theater = mapToEntity(theaterDto);
        Theater savedTheater =  theaterRepository.save(theater);
        return mapToDto(savedTheater);
    }

    public TheaterDto getTheaterById(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Theater not found with id " + id));
        return mapToDto(theater);
    }

    public List<TheaterDto> getAllTheaters() {
        List<Theater> theaters = theaterRepository.findAll();
        return theaters.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TheaterDto updateTheater(Long id,TheaterDto theaterDto){
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Theater not found with id " + id));

        theater.setName(theaterDto.getName());
        theater.setAddress(theaterDto.getAddress());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreens(theaterDto.getTotalScreens());

        return mapToDto(theater);
    }

    public void deleteTheater(Long id){
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Theater not found with id " + id));
        theaterRepository.delete(theater);
    }

    public List<TheaterDto> getAllTheaterByCity(String city) {
        List<Theater> theaters = theaterRepository.findByCity(city);
        return theaters.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }



    public TheaterDto mapToDto(Theater theater) {
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theater.getId());
        theaterDto.setName(theater.getName());
        theaterDto.setAddress(theater.getAddress());
        theaterDto.setCity(theater.getCity());
        theaterDto.setTotalScreens(theater.getTotalScreens());

        return theaterDto;
    }

    public Theater mapToEntity(TheaterDto theaterDto) {
        Theater theater = new Theater();
        theater.setId(theaterDto.getId());
        theater.setName(theaterDto.getName());
        theater.setAddress(theaterDto.getAddress());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreens(theaterDto.getTotalScreens());

        return theater;
    }
}
