package com.danio.alkemy.service;

import com.danio.alkemy.entity.Character;
import com.danio.alkemy.entity.Movie;
import com.danio.alkemy.exception.CharacterIsAlreadyAssignException;
import com.danio.alkemy.exception.MovieNotFoundException;
import com.danio.alkemy.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final CharacterService characterService;
    private final GenreService genreService;

    @Autowired
    public MovieService(MovieRepository movieRepository, CharacterService characterService, GenreService genreService) {
        this.movieRepository = movieRepository;
        this.characterService = characterService;
        this.genreService = genreService;
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie saveMovie(Movie movie) {
        movie.setGenre(genreService.findGenreByGenreType(movie.getGenreType()));
        movieRepository.save(movie);
        genreService.addMovieToGenre(this.findMovieById(movie.getId()));
        return movie;
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Transactional
    public Movie editMovie(Long id, Movie movie) {
        Movie movieToEdit = findMovieById(id);
        movieToEdit.setTitle(movie.getTitle());
        movieToEdit.setRating(movie.getRating());
        return movieToEdit;
    }

    @Transactional
    public Movie addCharacterToMovie(Long movieId, Long characterId) {
        Movie movie = findMovieById(movieId);
        Character character = characterService.findCharacterById(characterId);
        if (movie.getCharacters().stream().anyMatch(s -> s.getId().equals(characterId))) {//s.getId() == characterId)
            throw new CharacterIsAlreadyAssignException(movieId, characterId);
        }
        movie.addCharacter(character);
        character.addMovie(movie);
        return movie;
    }

    @Transactional
    public Movie removeCharacterFromMovie(Long movieId, Long characterId) {
        Movie movie = findMovieById(movieId);
        Character character = characterService.findCharacterById(characterId);
        movie.removeCharacter(character);
        character.removeMovie(movie);
        return movie;
    }

    @Transactional
    public void removeCharacterFromAllMovies(Character character) {
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(s -> s.removeCharacter(character));
    }

    public List<Character> findAllCharacters(Long id){
        Movie movie = findMovieById(id);
        return movie.getCharacters();
    }
}
