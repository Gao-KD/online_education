package com.gaokd.online_education.service;

import com.gaokd.online_education.model.entity.Video;
import com.gaokd.online_education.model.entity.VideoBannner;

import java.util.List;

public interface VideoService {
    /**
     * 视频列表接口
     * @return
     */
    List<Video> videoList();

    /**
     * 视频轮播图接口
     */
    List<VideoBannner> bannerList();;
    /**
     * 根据视频id查询视频的章、级
     */
    Video findDetailById(int videoId);

}
