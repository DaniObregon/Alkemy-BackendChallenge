package com.danio.alkemy.repository;

import com.danio.alkemy.entity.Genre;
import com.danio.alkemy.entity.GenreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    /**
     * TESTING
     * */
    Genre findGenreByGenreType(GenreType genreType);

//    @Query(value = "SELECT t FROM genre t WHERE t.genre_type=:genreType", nativeQuery = true)
//    public Genre findGenreByGenreType(GenreType genreType);

//    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))")
//    List<Tutorial> findByTitleLikeCaseInsensitive(String title);

}
