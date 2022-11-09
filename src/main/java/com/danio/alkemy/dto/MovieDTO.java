package com.danio.alkemy.dto;

import com.danio.alkemy.entity.Genre;
import com.danio.alkemy.entity.Movie;
import lombok.Data;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String rating;
    private Genre genre;

    public static MovieDTO from(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setRating(movie.getRating());
        return movieDTO;
    }
}
