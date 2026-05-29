package com.cfg.BMS.controller;

import com.cfg.BMS.dto.ShowDto;
import com.cfg.BMS.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<ShowDto> createShow(@RequestBody ShowDto showDto){
        return new ResponseEntity<>(showService.CreateShow(showDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ShowDto>> getAllShow(){
        return ResponseEntity.ok(showService.getAllShows());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getAllShow(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowById(id));
    }



}
