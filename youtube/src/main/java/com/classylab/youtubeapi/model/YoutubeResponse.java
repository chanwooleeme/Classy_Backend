package com.classylab.youtubeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
public class YoutubeResponse {
    private ArrayList<Item> items;

    private String nextPageToken;

    @Getter
    public static class Item {
        private String kind;
        private String etag;
        private Id id;
        private Snippet snippet;

        public String getPublishedAt() {
            return this.snippet.getPublishedAt();
        }
        public String getVideoId() {
            return this.id.getVideoId();
        }

        public String getTitle() {
            return this.snippet.getTitle();
        }

        public String getChannelTitle() { return this.snippet.getChannelTitle();}
        public Map<String, Quality> getThumbnailQualities() {
            Map<String, Quality> map = new HashMap<>();
            map.put("Default", getThumbnails().getDefault());
            map.put("Medium", getThumbnails().getMedium());
            map.put("High", getThumbnails().getHigh());
            return map;
        }

        private Thumbnails getThumbnails() {
            return this.snippet.getThumbnails();
        }
    }
    @Getter
    static class Id {
        private String kind;
        private String videoId;
    }

    @Getter
    static class Snippet {
        private String publishedAt;
        private String channelId;
        private String title;
        private String channelTitle;
        private String publishTime;
        private Thumbnails thumbnails;
    }

    @Getter
    public static class Thumbnails {
        @JsonProperty("default")
        private Quality Default;
        private Quality medium;
        private Quality high;
    }

}
