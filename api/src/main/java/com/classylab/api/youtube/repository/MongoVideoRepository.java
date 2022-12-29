package com.classylab.api.youtube.repository;

import com.classylab.api.youtube.model.MongoVideo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoVideoRepository extends MongoRepository<MongoVideo, String> {
    public List<MongoVideo> findByVideoId(String videoId);
}
