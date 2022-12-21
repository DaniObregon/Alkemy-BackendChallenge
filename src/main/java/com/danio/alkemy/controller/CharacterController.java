package com.danio.alkemy.controller;

import com.danio.alkemy.dto.CharacterDTO;
import com.danio.alkemy.entity.Character;
import com.danio.alkemy.service.CharacterService;
import com.danio.alkemy.service.MovieService;
import com.danio.alkemy.service.SeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;
    private final MovieService movieService;
    private final SeriesService seriesService;

    public CharacterController(CharacterService characterService, MovieService movieService, SeriesService seriesService) {
        this.characterService = characterService;
        this.movieService = movieService;
        this.seriesService = seriesService;
    }

    @PostMapping("/save")
    public ResponseEntity<CharacterDTO> saveCharacter(final CharacterDTO characterDTO) {
        Character character = Character.from(characterDTO);
        characterService.saveCharacter(character);
        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable final Long id) {
        Character character = characterService.findCharacterById(id);
        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterDTO>> getCharacters() {
        List<CharacterDTO> characterDTOS = characterService.findAllCharacters()
                .stream()
                .map(CharacterDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(characterDTOS, HttpStatus.OK);
    }

    /**
     * Edit Character: Solo editamos los datos principales del personaje,
     * dejando de lado los campos que poseen listas, ya que tales campos
     * se editan por medio de endpoints
     * @param id
     * @param
     * @return
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<CharacterDTO> editCharacter(
            @PathVariable final Long id,
            @RequestBody final CharacterDTO characterDTO) {
        Character character = characterService.editCharacter(id, Character.from(characterDTO));
        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
    }

    /**
     * Antes de eliminar un Character, debe eliminarse de la lista de Characters
     * asociados de cada Movie y decada Series!!!!
     * Si no se elimina dicho Character de cada entity, se genera una Exception
     * SQLIntegrityConstraintViolationException
     * @param id
     * @return no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        Character character = characterService.findCharacterById(id);
        movieService.removeCharacterFromAllMovies(character);
        seriesService.removeCharacterFromAllSeries(character);
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<List<CharacterDTO>> getCharactersFromMovie(@PathVariable final Long movieId){
        List<Character> characters = movieService.findAllCharacters(movieId);
        List<CharacterDTO> characterDTOS = characters
                .stream()
                .map(CharacterDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(characterDTOS, HttpStatus.OK);
    }

    @GetMapping("/series/{seriesId}")
    public ResponseEntity<List<CharacterDTO>> getCharactersFromSeries(@PathVariable final Long seriesId){
        List<Character> characters = seriesService.findAllCharacters(seriesId);
        List<CharacterDTO> characterDTOS = characters
                .stream()
                .map(CharacterDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(characterDTOS, HttpStatus.OK);
    }
}


/**
 * Metodo EDIT -> FUNCIONA TAMBIEN.
 * Es mas simple dejando que JPA infiera save o edit segun el id
 */
//    @PutMapping()
//    public ResponseEntity<CharacterDTO> editCharacter(@RequestBody Character character) {
//        characterService.saveCharacter(character);
//        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
//    }

/**
 * METODO EDIT -> FUNCIONA TAMBIEN
 */
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<MovieDTO> editMovie(
//            @PathVariable final Long id,
//            @RequestBody final MovieDTO movieDTO) {
//        Movie movie = movieService.editMovie(id, Movie.from(movieDTO));
//        return new ResponseEntity<>(MovieDTO.from(movie), HttpStatus.OK);
//    }

/**
 * METODO SAVE -> FUNCIONA PERO PERMITE LLENAR PARAMETROS LISTAS
 */
//    @PostMapping
//    public ResponseEntity<CharacterDTO> saveCharacter(final Character character) {
//        characterService.saveCharacter(character);
//        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
//    }