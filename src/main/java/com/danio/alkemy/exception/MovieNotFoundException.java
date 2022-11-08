package com.danio.alkemy.exception;

import java.text.MessageFormat;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long id) {
        super(MessageFormat.format("ERROR: Movie id: {0} Not Found.", id));
    }
}
