package com.gaokd.online_education.service.impl;

import com.gaokd.online_education.config.CacheKeyManager;
import com.gaokd.online_education.model.entity.Video;
import com.gaokd.online_education.model.entity.VideoBannner;
import com.gaokd.online_education.mapper.VideoMapper;
import com.gaokd.online_education.service.VideoService;
import com.gaokd.online_education.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    /**
     * videoList（）、findDetailById()、 bannerList()均引入缓存
     * 只需访问数据库一次，之后每次都访问缓存
     * @return
     */
    @Override
    public List<Video> videoList() {
        try{
            Object objectCache = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST_KEY,()->{
                List<Video> videoList = videoMapper.videoList();
                return videoList;
            });
            if (objectCache instanceof List){
                List<Video> videoList = (List<Video>) objectCache;
                return videoList;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Video findDetailById(int videoId) {
        try {
            //有个videoId的值，需要格式化并且赋值
            String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL,videoId);

            Object objectCache = baseCache.getOneHourCache().get(videoCacheKey,()->{
                Video video = videoMapper.findDetailById(videoId);
                return video;
            });
            if (objectCache instanceof Video){
                Video video = (Video) objectCache;
                return video;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public List<VideoBannner> bannerList(){
        //先根据key查找，然后返回Object,如果没查到就去数据库中找，找到就存入缓存中
        try {                                                    //lambda表达式，类似if-else
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{
                List<VideoBannner> bannerList = videoMapper.bannerList();
                return bannerList;
            });
            //判断是不是List子类型
            if (cacheObj instanceof List){
                List<VideoBannner> bannnerList  = (List<VideoBannner>) cacheObj;
                return bannnerList;
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
