package com.gaokd.online_education.mapper;

import com.gaokd.online_education.model.entity.Episode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeMapper {
    Episode findFirstByVideoId(@Param("video_id") int videoId);
}
