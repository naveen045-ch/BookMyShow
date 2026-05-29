package com.cfg.BMS.controller;

import com.cfg.BMS.dto.MovieDto;
import com.cfg.BMS.dto.TheaterDto;
import com.cfg.BMS.service.TheaterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public ResponseEntity<List<TheaterDto>> getAllTheaters(){
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @PostMapping
    public ResponseEntity<TheaterDto> createTheater(@Valid @RequestBody TheaterDto theaterDto){
        return new ResponseEntity<>(theaterService.createTheater(theaterDto),HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TheaterDto> getTheaterById(@PathVariable Long id){
        TheaterDto theaterDto = theaterService.getTheaterById(id);
        if(theaterDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }
}
