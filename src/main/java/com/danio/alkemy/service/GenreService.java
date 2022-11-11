package com.danio.alkemy.service;

import com.danio.alkemy.entity.Genre;
import com.danio.alkemy.entity.GenreType;
import com.danio.alkemy.entity.Movie;
import com.danio.alkemy.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class GenreService {
    private final GenreRepository genreRepository;


    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findGenreByGenreType(GenreType genreType) {
        return genreRepository.findGenreByGenreType(genreType);
    }


    //TODO: Hacer algo con la image del objeto Genre que se inserta en la DB
    public Genre saveGenre(Genre genre, MultipartFile file) {
        return genreRepository.save(genre);
    }


    //TODO: probar metodo para agregar Movie a Genre
    //TODO: luego de guardar una Movie, se deberia guardar tambien en el Genre
    // por medio del servicio de Movie llamando al servicio GenreService
    @Transactional
    public Genre addMovieToGenre(Movie movie) {
        Genre genre = genreRepository.findGenreByGenreType(movie.getGenreType());
        genre.addMovie(movie);
        return genre;
    }
}