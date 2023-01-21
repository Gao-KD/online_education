package com.gaokd.online_education.mapper;

import com.gaokd.online_education.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoMapper {
    /**
     * 查询视频列表
     * @return
     */
    List<Video> videoList();
}
