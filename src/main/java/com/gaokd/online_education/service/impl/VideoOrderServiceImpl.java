package com.gaokd.online_education.service.impl;

import com.gaokd.online_education.exception.OException;
import com.gaokd.online_education.mapper.*;
import com.gaokd.online_education.model.entity.Episode;
import com.gaokd.online_education.model.entity.PlayRecord;
import com.gaokd.online_education.model.entity.Video;
import com.gaokd.online_education.model.entity.VideoOrder;
import com.gaokd.online_education.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    /**
     * 下单操作
     * 未来版本，优惠券抵扣，风控用户信息，生成订单基础
     * @param userId
     * @param videoId
     * @return
     */
    @Transactional
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

        /**
         * 如果订单更新了，保存播放记录
         */
        if (rows == 1){
            Episode episode = episodeMapper.findFirstByVideoId(videoId);
            if (episode == null){
                throw new OException(-1,"视频没有集信息");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);

            //插入播放历史记录
            playRecordMapper.saveRecode(playRecord);

        }
        return rows;
    }

    /**
     * 查找用户订单列表
     * @param userId
     * @return
     */
    @Override
    public List<VideoOrder> orderListByUserId(Integer userId) {
        List<VideoOrder> videoOrderList = videoOrderMapper.orderListByUserId(userId);
        return videoOrderList;
    }
}
