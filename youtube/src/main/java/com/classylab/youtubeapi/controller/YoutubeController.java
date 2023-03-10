package com.classylab.youtubeapi.controller;

import com.classylab.youtubeapi.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class YoutubeController {
    private final YoutubeService youtubeFeignService;

    @GetMapping("/save")
    public ResponseEntity<String> saveYoutubeVideo() {
        youtubeFeignService.saveYoutubeData();
        return ResponseEntity.ok("ok");
    }
}
