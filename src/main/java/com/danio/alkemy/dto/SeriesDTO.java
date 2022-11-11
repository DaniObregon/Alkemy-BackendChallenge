package com.danio.alkemy.dto;

import com.danio.alkemy.entity.Series;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SeriesDTO {

    @ApiModelProperty(required = false, hidden = true)
    private Long id;
    private String title;
    private String rating;

    public static SeriesDTO from(Series series){
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setId(series.getId());
        seriesDTO.setTitle(series.getTitle());
        seriesDTO.setRating(series.getRating());
        return seriesDTO;
    }
}
