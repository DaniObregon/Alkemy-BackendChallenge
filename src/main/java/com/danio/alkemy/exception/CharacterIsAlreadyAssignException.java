package com.danio.alkemy.exception;

import java.text.MessageFormat;

//TODO: Factorizar Exception si es Movie o si es Series

public class CharacterIsAlreadyAssignException extends CharacterException {
    public CharacterIsAlreadyAssignException(Long movieId, Long characterId) {
        super(MessageFormat.format(
                "ERROR: cannot assign Character {1} to Movie {0}", movieId, characterId));
    }
}