package com.classylab.api.youtube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
@Getter
@AllArgsConstructor
public class MongoVideo {

    @Id
    private String videoId;

    private String genre;

    private String title;

    private String channelTitle;

    private String publishedAt;

    private Thumbnails thumbnails;

    @Setter
    @Getter
    public static class Thumbnails {
        private MongoThumbnail normal;
        private MongoThumbnail high;
        private MongoThumbnail low;
    }

    @Setter
    @Getter
    public static class MongoThumbnail {
        private String url;
        private String quality;
        private String width;
        private String height;
    }
}
