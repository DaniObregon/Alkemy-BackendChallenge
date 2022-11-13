package com.danio.alkemy.controller;

import com.danio.alkemy.service.GenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
