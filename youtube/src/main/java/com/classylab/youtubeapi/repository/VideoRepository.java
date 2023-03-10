package com.classylab.youtubeapi.repository;

import com.classylab.youtubeapi.model.Genre;
import com.classylab.youtubeapi.model.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByGenre(Genre genre, Pageable pageable);

    List<Video> findAllByGenreInOrderByRelevance(List<Genre> genres, Pageable pageable);

    Video findTopByGenreOrderByIdDesc(Genre genre);

    Video findByVideoId(String videoId);

    boolean existsDanceByVideoId(String videoId);
}
