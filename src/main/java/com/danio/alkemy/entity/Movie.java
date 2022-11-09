package com.danio.alkemy.entity;

import com.danio.alkemy.dto.MovieDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String rating;

    private GenreType genreType;

    /**
     * TESTING
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_genre_FK")
    private Genre genre;

/*************************************************************************************/

//    @ApiModelProperty(value = "yyyy/MM/dd", example = "2022/02/22")
//    private Date date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_characters",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")})
    private List<Character> characters = new ArrayList<>();

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public static Movie from(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setRating(movieDTO.getRating());
        return movie;
    }
}
