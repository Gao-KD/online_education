package com.gaokd.online_education.controller;

import com.gaokd.online_education.model.entity.Video;
import com.gaokd.online_education.model.entity.VideoBannner;
import com.gaokd.online_education.service.VideoService;
import com.gaokd.online_education.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController                     //能够返回json格式
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 视频列表
     */
    @RequestMapping("list")
    public JsonData videoList(){
        List<Video> videoList = videoService.videoList();
        return videoList!=null?JsonData.buildSucess(videoList):JsonData.buildError("视频列表为空");
    }

    /**
     * 轮播图列表
     * @return
     */
    @RequestMapping("banner_list")
    public JsonData indexBanner(){
        List<VideoBannner> bannerList = videoService.bannerList();
        return bannerList!=null? JsonData.buildSucess(bannerList):JsonData.buildError("轮播图列表为空");
    }

    /**
     * 根据视频id查询
     * @return
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id") int videoId){
        Video video = videoService.findDetailById(videoId);
        return video!=null?JsonData.buildSucess(video):JsonData.buildSucess("查询不到该视频");
    }

}
