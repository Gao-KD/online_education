package com.gaokd.online_education.service.impl;

import com.gaokd.online_education.mapper.UserMapper;
import com.gaokd.online_education.mapper.VideoMapper;
import com.gaokd.online_education.mapper.VideoOrderMapper;
import com.gaokd.online_education.model.entity.Video;
import com.gaokd.online_education.model.entity.VideoOrder;
import com.gaokd.online_education.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 下单操作
     * 未来版本，优惠券抵扣，风控用户信息，生成订单基础
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    public int save(int userId, int videoId) {
        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoAndState(userId,videoId,1);
        if (videoOrder!=null) {return 0;}

        Video video = videoMapper.findById(videoId);
        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoTitle(video.getTitle());
        newVideoOrder.setVideoImg(video.getCoverImg());
        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        return rows;
    }
}
