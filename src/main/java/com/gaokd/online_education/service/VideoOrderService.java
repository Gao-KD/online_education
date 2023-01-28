package com.gaokd.online_education.service;

import com.gaokd.online_education.model.entity.Video;
import com.gaokd.online_education.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    /**
     * 保存订单
     */
    int save(int userId,int videoId);

    List<VideoOrder> orderListByUserId(Integer userId);
    /**
     * 根据视频id查询视频的章、级
     */
}
