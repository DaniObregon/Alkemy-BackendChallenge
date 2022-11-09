package com.danio.alkemy.entity;

import com.danio.alkemy.dto.MovieDTO;
import com.danio.alkemy.dto.SeriesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String rating;

//    @ApiModelProperty(value = "yyyy/MM/dd", example = "2022/02/22")
//    private Date date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "series_characters",
            joinColumns = {@JoinColumn(name = "series_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")})
    private List<Character> characters = new ArrayList<>();

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public static Series from(SeriesDTO seriesDTO){
        Series series = new Series();
        series.setTitle(seriesDTO.getTitle());
        series.setRating(seriesDTO.getRating());
        return series;
    }
}