package com.classylab.api.youtube.service;

import com.classylab.api.youtube.model.VideoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youtube.model.Genre;
import youtube.model.Video;
import youtube.repository.VideoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoListResponse findVideosByGenres(String genres, Pageable pageable) {
        return VideoListResponse.create(findByGenres(stringToGenreList(genres), pageable));
    }

    @Transactional(readOnly = true)
    public List<Video> findByGenres(List<Genre> genres, Pageable pageable) {
        return videoRepository.findAllByGenreInOrderByRelevance(genres, pageable);
    }

    private List<Genre> stringToGenreList(String genres) {
        return Arrays.stream(genres.split(" ")).map(Genre::nameOf).collect(Collectors.toList());
    }
}
