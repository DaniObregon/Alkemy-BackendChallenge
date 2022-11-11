package com.danio.alkemy.controller;

import com.danio.alkemy.dto.CharacterDTO;
import com.danio.alkemy.dto.MovieDTO;
import com.danio.alkemy.entity.Character;
import com.danio.alkemy.entity.Genre;
import com.danio.alkemy.entity.GenreType;
import com.danio.alkemy.entity.Movie;
import com.danio.alkemy.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

//    @GetMapping("/{genreType}")
//    public ResponseEntity<Genre> getGenreById(@PathVariable final GenreType genreType){
//        Genre genre = genreService.findGenreByGenreType(genreType);
//        return new ResponseEntity<>(genre, HttpStatus.OK);
//    }

    /**
     * FUNCIONA
     * @param genreType
     * @return
     */
//    @GetMapping("/{genreType}")
//    public ResponseEntity<List<MovieDTO>> getMoviesByGenre(@PathVariable final GenreType genreType){
//        Genre genre = genreService.findGenreByGenreType(genreType);
//        List<Movie> movies = genre.getRelatedMovies();
//        List<MovieDTO> moviesDTO = movies.stream().map(MovieDTO::from).collect(Collectors.toList());
//        return new ResponseEntity<>(moviesDTO, HttpStatus.OK);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable final Long id) {
//        Character character = characterService.findCharacterById(id);
//        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
//    }
}
