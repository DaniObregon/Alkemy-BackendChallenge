package com.danio.alkemy.controller;

import com.danio.alkemy.dto.SeriesDTO;
import com.danio.alkemy.entity.Series;
import com.danio.alkemy.service.SeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SeriesController {
    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping
    public ResponseEntity<SeriesDTO> saveSeries(Series series) {
        seriesService.saveSeries(series);
        return new ResponseEntity<>(SeriesDTO.from(series), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SeriesDTO>> getSeries() {
        List<SeriesDTO> seriesDTOS = seriesService.findAllSeries()
                .stream()
                .map(SeriesDTO::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(seriesDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesDTO> getSeriesById(@PathVariable final Long id) {
        Series series = seriesService.findSeriesById(id);
        return new ResponseEntity<>(SeriesDTO.from(series), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeriesById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SeriesDTO> editSeries(
            @PathVariable final Long id,
            @RequestBody final SeriesDTO seriesDTO) {
        Series series = seriesService.editSeries(id, Series.from(seriesDTO));
        return new ResponseEntity<>(SeriesDTO.from(series), HttpStatus.OK);
    }

    @PutMapping(value = "{seriesId}/characters/{characterId}/add")
    public ResponseEntity<SeriesDTO> addCharacterToSeries(
            @PathVariable final Long seriesId,
            @PathVariable final Long characterId) {
        Series series = seriesService.addCharacterToSeries(seriesId, characterId);
        return new ResponseEntity<>(SeriesDTO.from(series), HttpStatus.OK);
    }

    @PutMapping(value = "{seriesId}/characters/{characterId}/remove")
    public ResponseEntity<SeriesDTO> removeCharacterFromMovie(
            @PathVariable final Long seriesId,
            @PathVariable final Long characterId) {
        Series series = seriesService.removeCharacterFromSeries(seriesId, characterId);
        return new ResponseEntity<>(SeriesDTO.from(series), HttpStatus.OK);
    }
}
