package com.cfg.BMS.controller;

import com.cfg.BMS.dto.MovieDto;
import com.cfg.BMS.repository.MovieRepository;
import com.cfg.BMS.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto)
    {
        return new ResponseEntity<>(movieService.createMovie(movieDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id)
    {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies()
    {

        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id,@RequestBody MovieDto movieDto){
        MovieDto UpdatedMovie = movieService.updateMovie(id,movieDto);
        if(UpdatedMovie == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(UpdatedMovie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> patchMovie(@PathVariable Long id,@RequestBody String releaseDate){
        MovieDto updateReleasedDate = movieService.updateReleasedDate(id,releaseDate);
        if(updateReleasedDate == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updateReleasedDate);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return  ResponseEntity.noContent().build();
    }
}
