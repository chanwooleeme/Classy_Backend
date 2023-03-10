package com.classylab.youtubeapi.service;


import com.classylab.youtubeapi.client.YoutubeClient;
import com.classylab.youtubeapi.model.*;
import com.classylab.youtubeapi.repository.ThumbnailRepository;
import com.classylab.youtubeapi.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class YoutubeService {

    private final YoutubeClient youtubeClient;
    private final VideoRepository videoRepository;
    private final ThumbnailRepository thumbnailRepository;

    private Integer MAX_PAGE = 5;
    private Long relevence;

    public void saveYoutubeData() {
        for(Genre genre : Genre.values()) {
            setRelevenceOfGenre(genre);
            getYoutubeResponseByKeyword(genre.name());
        }
    }

    @Transactional(readOnly = true)
    public void setRelevenceOfGenre(Genre genre) {
        Video video = videoRepository.findTopByGenreOrderByIdDesc(genre);
        if (video != null)
            relevence = video.getRelevance();
        else
            relevence = 0L;
    }

    public void getYoutubeResponseByKeyword(String keyword) {
        String pageToken = "";
        int page = 0;

        while(page < MAX_PAGE) {
            YoutubeResponse response = youtubeClient.getSearchResponse(keyword, pageToken);
            if (response.getNextPageToken() == null) break;
            pageToken = response.getNextPageToken();
            saveYoutubeResponse(response, Genre.nameOf(keyword));
            page += 1;
        }
    }

    @Transactional
    public void saveYoutubeResponse(YoutubeResponse youtubeResponse, Genre genre) {
        for (YoutubeResponse.Item item  : youtubeResponse.getItems()) {
            if (videoRepository.existsDanceByVideoId(item.getVideoId())) continue;

            Video video = Video.create(genre, item.getTitle(), item.getChannelTitle(), item.getVideoId(), item.getPublishedAt(), ++relevence);
            video = videoRepository.save(video);

            Map<String, Quality> qualities = item.getThumbnailQualities();

            for (String key:qualities.keySet()) {
                Quality quality = qualities.get(key);
                Thumbnail thumbnail = Thumbnail.create(key, quality.getUrl(), quality.getWidth(), quality.getHeight());
                thumbnail.setVideo(video);
                thumbnailRepository.save(thumbnail);
            }
        }
    }
}
