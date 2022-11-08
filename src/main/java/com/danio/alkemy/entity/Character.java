package com.danio.alkemy.entity;

import com.danio.alkemy.dto.CharacterDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String age;

    private double weight;

    private String story;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "characters")
    private List<Movie> moviesList = new ArrayList<>();

    /**
     * TESTING -> TESTING OK
     *************************************************************************************/
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "characters")
    private List<Series> seriesList = new ArrayList<>();

    public void addSeries(Series series){
        seriesList.add(series);
    }

    public void removeSeries(Series series){
        seriesList.remove(series);
    }

    /**************************************************************************************/

    public void addMovie(Movie movie) {
        moviesList.add(movie);
    }

    public void removeMovie(Movie movie) {
        moviesList.remove(movie);
    }

    public static Character from(CharacterDTO characterDTO){
        Character character = new Character();
        character.setName(characterDTO.getName());
        character.setAge(characterDTO.getAge());
        character.setWeight(character.getWeight());
        character.setStory(character.getStory());
        return character;
    }
}