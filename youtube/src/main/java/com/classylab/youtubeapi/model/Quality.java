package com.classylab.youtubeapi.model;

import lombok.Getter;

@Getter
public class Quality {
    private String url;
    private String width;
    private String height;

    public static Quality create(Thumbnail thumbnail) {
        Quality quality = new Quality();
        quality.url = thumbnail.getUrl();
        quality.width = thumbnail.getWidth();
        quality.height = thumbnail.getHeight();
        return quality;
    }
}