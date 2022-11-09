package com.danio.alkemy.dto;

import com.danio.alkemy.entity.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class CharacterDTO {

    //    @ApiParam(hidden = true)
//    @JsonIgnore
    @ApiModelProperty(required = false, hidden = true)//Funciona!
    private Long id;
    private String name;
    private String age;
    private double weight;
    private String story;

    public static CharacterDTO from(Character character) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(character.getId());
        characterDTO.setName(character.getName());
        characterDTO.setAge(character.getAge());
        characterDTO.setWeight(character.getWeight());
        characterDTO.setStory(character.getStory());
        return characterDTO;
    }
}