package com.gaokd.online_education.service;

import com.gaokd.online_education.model.entity.Video;

public interface VideoOrderService {
    /**
     * 保存订单
     */
    int save(int userId,int videoId);
    /**
     * 根据视频id查询视频的章、级
     */
}
