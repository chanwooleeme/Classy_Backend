package com.classylab.api.youtube.repository;

import com.classylab.api.youtube.model.MongoVideo;
import com.classylab.api.youtube.model.Quality;
import com.classylab.api.youtube.model.Thumbnail;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MongoVideoRepositoryTest {

    @Autowired
    private MongoVideoRepository repository;

    @Test
    void saveTest() {
        MongoVideo.MongoThumbnail normal = new MongoVideo.MongoThumbnail();
        normal.setQuality("normal");
        normal.setHeight("Height");
        normal.setUrl("URL");
        normal.setWidth("WIDTH");

        MongoVideo.MongoThumbnail high = new MongoVideo.MongoThumbnail();
        high.setQuality("high");
        high.setHeight("Height");
        high.setUrl("URL");
        high.setWidth("WIDTH");

        MongoVideo.MongoThumbnail low = new MongoVideo.MongoThumbnail();
        low.setQuality("low");
        low.setHeight("Height");
        low.setUrl("URL");
        low.setWidth("WIDTH");

        MongoVideo.Thumbnails thumbnails = new MongoVideo.Thumbnails();
        thumbnails.setHigh(high);
        thumbnails.setNormal(normal);
        thumbnails.setLow(low);

        MongoVideo mongoVideo = new MongoVideo("ID", "GENRE", "TITLE", "CHANNEL", "TODAY", thumbnails);
        repository.save(mongoVideo);

        assertThat("ID").isEqualTo(repository.findByVideoId("ID").get(0).getVideoId());
    }
}