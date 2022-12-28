package com.classylab.api.youtube.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import youtube.model.Quality;
import youtube.model.Thumbnail;

import java.util.List;


@Getter
public class ThumbnailResponse {

    @JsonProperty("default")
    private Quality defaultQuality;

    @JsonProperty("medium")
    private Quality mediumQuality;

    @JsonProperty("high")
    private Quality highQuality;

    public static ThumbnailResponse from(List<Thumbnail> thumbnails) {
        ThumbnailResponse response = new ThumbnailResponse();
        for (Thumbnail thumbnail : thumbnails) {
            if ("Default".equals(thumbnail.getQuality()))
                response.defaultQuality = Quality.create(thumbnail);
            else if("Medium".equals(thumbnail.getQuality()))
                response.mediumQuality = Quality.create(thumbnail);
            else
                response.highQuality = Quality.create(thumbnail);
        }
        return response;
    }
}
