package com.danio.alkemy.exception;

import java.text.MessageFormat;

public class SeriesNotFoundException extends RuntimeException {
    public SeriesNotFoundException(Long id) {
        super(MessageFormat.format("ERROR: Series id: {0} Not Found", id));
    }
}
