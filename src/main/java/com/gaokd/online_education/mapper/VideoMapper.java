package com.gaokd.online_education.mapper;

import com.gaokd.online_education.model.entity.Video;
import com.gaokd.online_education.model.entity.VideoBannner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoMapper {
    /**
     * 查询视频列表
     * @return
     */
    List<Video> videoList();

    /**
     * 视频轮播图
     * @return
     */
    List<VideoBannner> bannerList();
    /**
     * 根据视频id查询视频的详细信息
     */
    Video findDetailById(@Param("video_id") int videoId);
}
