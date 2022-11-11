package com.danio.alkemy.controller;

import com.danio.alkemy.dto.MovieDTO;
import com.danio.alkemy.entity.Genre;
import com.danio.alkemy.entity.GenreType;
import com.danio.alkemy.entity.Movie;
import com.danio.alkemy.service.GenreService;
import com.danio.alkemy.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final GenreService genreService;

    public MovieController(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<MovieDTO> saveMovie(final MovieDTO movieDTO) {
        Movie movie = Movie.from(movieDTO);
        movieService.saveMovie(movie);
        return new ResponseEntity<>(MovieDTO.from(movie), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMovies() {
        List<MovieDTO> movieDTOS = movieService.findAllMovies()
                .stream()
                .map(MovieDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(movieDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable final Long id) {
        Movie movie = movieService.findMovieById(id);
        return new ResponseEntity<>(MovieDTO.from(movie), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> editMovie(
            @PathVariable final Long id,
            @RequestBody final MovieDTO movieDTO) {
        Movie movie = movieService.editMovie(id, Movie.from(movieDTO));
        return new ResponseEntity<>(MovieDTO.from(movie), HttpStatus.OK);
    }

    @PutMapping(value = "{movieId}/characters/{characterId}/add")
    public ResponseEntity<MovieDTO> addCharacterToMovie(
            @PathVariable final Long movieId,
            @PathVariable final Long characterId) {
        Movie movie = movieService.addCharacterToMovie(movieId, characterId);
        return new ResponseEntity<>(MovieDTO.from(movie), HttpStatus.OK);
    }

    @PutMapping(value = "{movieId}/characters/{characterId}/remove")
    public ResponseEntity<MovieDTO> removeCharacterFromMovie(
            @PathVariable final Long movieId,
            @PathVariable final Long characterId) {
        Movie movie = movieService.removeCharacterFromMovie(movieId, characterId);
        return new ResponseEntity<>(MovieDTO.from(movie), HttpStatus.OK);
    }

    @GetMapping("/genres/{genreType}")
    public ResponseEntity<List<MovieDTO>> getMoviesByGenre(@PathVariable final GenreType genreType){
        Genre genre = genreService.findGenreByGenreType(genreType);
        List<Movie> movies = genre.getRelatedMovies();
        List<MovieDTO> moviesDTO = movies.stream().map(MovieDTO::from).collect(Collectors.toList());
        return new ResponseEntity<>(moviesDTO, HttpStatus.OK);
    }
}
/**
 * SAVE MOVIE Funciona
 */
//    @PostMapping
//    public ResponseEntity<MovieDTO> saveMovie(Movie movie) {
//        movieService.saveMovie(movie);
//        return new ResponseEntity<>(MovieDTO.from(movie), HttpStatus.OK);
//    }