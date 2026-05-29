package com.cfg.BMS.controller;

import com.cfg.BMS.dto.ScreenDto;
import com.cfg.BMS.model.Screen;
import com.cfg.BMS.repository.ScreenRepository;
import com.cfg.BMS.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @PostMapping
    public ResponseEntity<ScreenDto> createScreen(@RequestBody ScreenDto screenDto) {

       return new ResponseEntity<>(screenService.createScreen(screenDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScreenDto>> getAllScreens() {

        return ResponseEntity.ok(screenService.getAllScreens());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenDto> getScreenById(@PathVariable Long id) {

        return ResponseEntity.ok(screenService.getScreenById(id));

    }



}
