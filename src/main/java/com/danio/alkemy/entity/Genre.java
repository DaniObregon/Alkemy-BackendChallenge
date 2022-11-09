package com.danio.alkemy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private GenreType genreType;

    /**
     * TESTING
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    @JsonIgnore
    private List<Movie> relatedMovies = new ArrayList<>();

/***************************************************************************************/

//    private List<Movie> movieList = new ArrayList<>();
//
//    private List<Series> seriesList = new ArrayList<>();

}
