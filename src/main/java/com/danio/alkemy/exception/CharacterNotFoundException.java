package com.danio.alkemy.exception;

import java.text.MessageFormat;

public class CharacterNotFoundException extends CharacterException {
    public CharacterNotFoundException(Long id) {
        super(MessageFormat.format("ERROR: Character id: {0} Not Found", id));
    }
}