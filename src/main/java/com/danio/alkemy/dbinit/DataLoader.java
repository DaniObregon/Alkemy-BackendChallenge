package com.danio.alkemy.dbinit;

import com.danio.alkemy.entity.Genre;
import com.danio.alkemy.entity.GenreType;
import com.danio.alkemy.service.GenreService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Esta clase inicializa la DB con generos por defecto
 */
@Component
public class DataLoader implements ApplicationRunner {

    private MultipartFile file;
    private final String genreImageFolder = "src/main/resources/static/image";
    private final GenreService genreService;

    public DataLoader(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Este metodo evalua si los generos ya fueron insertados en la DB
     * si la lista es == 0, se crean los generos, de lo contraio no hace nada.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (genreService.findAll().size() == 0) {
            createGenre("/adventure.png", "Adventure");
            createGenre("/comedy.png", "Comedy");
            createGenre("/documentary.png", "Documentary");
            createGenre("/family.png", "Family");
            createGenre("/musical.png", "Musical");
            createGenre("/sci-fi.png", "Scifi");
        }
    }

    private void createGenre(String image, String name) {
        Genre genre = new Genre();
        File file = new File(genreImageFolder + image);
        try {
            FileInputStream input = new FileInputStream(file);
            this.file = new MockMultipartFile("NEWFILE!", file.getName(), MediaType.IMAGE_JPEG_VALUE, input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        genre.setGenreType(GenreType.valueOf(name.toUpperCase()));
        genre.setName(name);
        genreService.saveGenre(genre, this.file);
    }
}