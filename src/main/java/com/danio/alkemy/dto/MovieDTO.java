package com.danio.alkemy.dto;

import com.danio.alkemy.entity.Genre;
import com.danio.alkemy.entity.GenreType;
import com.danio.alkemy.entity.Movie;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MovieDTO {

    @ApiModelProperty(required = false, hidden = true)
    private Long id;
    private String title;
    private String rating;
    private GenreType genreType;

    public static MovieDTO from(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setRating(movie.getRating());
        movieDTO.setGenreType(movie.getGenreType());
        return movieDTO;
    }
}
