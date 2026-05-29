package com.cfg.BMS.service;

import com.cfg.BMS.dto.MovieDto;
import com.cfg.BMS.exception.ResourceNotFoundException;
import com.cfg.BMS.model.Movie;
import com.cfg.BMS.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = mapToEntity(movieDto);
        Movie saveMovie = movieRepository.save(movie);
        return mapToDto(saveMovie);
    }

    public MovieDto getMovieById(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not Found"));
        return mapToDto(movie);
    }

    public List<MovieDto> getMovieByLanguage(String language){
        List<Movie> movies = movieRepository.findByLanguage(language);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByGenre(String genre){
        List<Movie> movies = movieRepository.findByGenre(genre);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> searchMovie(String title){
        List<Movie> movies = movieRepository.findByTitleContaining(title);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long id, MovieDto movieDto){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not Found"));

        movie.setTitle(movieDto.getTitle());
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setGenre(movieDto.getGenre());
        movie.setLanguage(movieDto.getLanguage());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setDescription(movieDto.getDescription());

        Movie UpdateMovie = movieRepository.save(movie);
        return mapToDto(UpdateMovie);
    }

    public void deleteMovie(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Movie not Found with id : "+id));
        movieRepository.delete(movie);
    }

    public MovieDto mapToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setPosterUrl(movie.getPosterUrl());
        movieDto.setReleaseDate(movie.getReleaseDate());
        movieDto.setDurationMins(movie.getDurationMins());
        movieDto.setGenre(movie.getGenre());
        movieDto.setLanguage(movie.getLanguage());
        return movieDto;
    }

    public Movie mapToEntity(MovieDto movieDto){

        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setGenre(movieDto.getGenre());
        movie.setLanguage(movieDto.getLanguage());
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setReleaseDate(movieDto.getReleaseDate());

        return movie;
    }
    public MovieDto updateReleasedDate(Long id, String releaseDate) {

        Movie movie = movieRepository.findById(id)
                .orElse(null);

        if (movie == null) {
            return null;
        }

        movie.setReleaseDate(releaseDate);

        Movie savedMovie = movieRepository.save(movie);

        return mapToDto(savedMovie);
    }
}
