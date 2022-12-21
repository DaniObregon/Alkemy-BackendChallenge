package com.danio.alkemy.service;

import com.danio.alkemy.entity.Character;
import com.danio.alkemy.entity.Movie;
import com.danio.alkemy.entity.Series;
import com.danio.alkemy.exception.CharacterIsAlreadyAssignException;
import com.danio.alkemy.exception.SeriesNotFoundException;
import com.danio.alkemy.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SeriesService {
    private final SeriesRepository seriesRepository;
    private final CharacterService characterService;
    private final GenreService genreService;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository, CharacterService characterService, GenreService genreService) {
        this.seriesRepository = seriesRepository;
        this.characterService = characterService;
        this.genreService = genreService;
    }

    public Series findSeriesById(Long id) {
        return seriesRepository.findById(id).orElseThrow(() -> new SeriesNotFoundException(id));
    }

    public Series saveSeries(Series series) {
        series.setGenre(genreService.findGenreByGenreType(series.getGenreType()));
        seriesRepository.save(series);
        genreService.addSeriesToGenre(this.findSeriesById(series.getId()));
        return series;
    }

    public List<Series> findAllSeries() {
        return seriesRepository.findAll();
    }

    public void deleteSeriesById(Long id) {
        seriesRepository.deleteById(id);
    }

    @Transactional
    public Series editSeries(Long id, Series series){
        Series seriesToEdit = findSeriesById(id);
        seriesToEdit.setTitle(series.getTitle());
        seriesToEdit.setRating(series.getRating());
        return seriesToEdit;
    }

    @Transactional
    public Series addCharacterToSeries(Long seriesId, Long characterId) {
        Series series = findSeriesById(seriesId);
        Character character = characterService.findCharacterById(characterId);
        if (series.getCharacters().stream().anyMatch(s -> s.getId() == characterId)) {
            throw new CharacterIsAlreadyAssignException(seriesId, characterId);
        }
        series.addCharacter(character);
        character.addSeries(series);
        return series;
    }

    @Transactional
    public Series removeCharacterFromSeries(Long seriesId, Long characterId) {
        Series series = findSeriesById(seriesId);
        Character character = characterService.findCharacterById(characterId);
        series.removeCharacter(character);
        character.removeSeries(series);
        return series;
    }

    @Transactional
    public void removeCharacterFromAllSeries(Character character) {
        List<Series> series = seriesRepository.findAll();
        series.forEach(s -> s.removeCharacter(character));
    }

    public List<Character> findAllCharacters(Long id){
        Series series = findSeriesById(id);
        return series.getCharacters();
    }
}
