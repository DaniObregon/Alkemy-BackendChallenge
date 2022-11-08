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

    @PostMapping
    public ResponseEntity<CharacterDTO> saveCharacter(Character character) {
        characterService.saveCharacter(character);
        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable final Long id) {
        Character character = characterService.findCharacterById(id);
        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
    }

    @GetMapping
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
            @RequestBody final Character character) {
        characterService.editCharacter(id, character);
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
}


/**
 * Metodo EDIT -> Este funciona. Es el mas simple
 * METODO EDIT -> FUNCIONA TAMBIEN
 */
//    @PutMapping()
//    public ResponseEntity<CharacterDTO> editCharacter(@RequestBody Character character) {
//        characterService.saveCharacter(character);
//        return new ResponseEntity<>(CharacterDTO.from(character), HttpStatus.OK);
//    }