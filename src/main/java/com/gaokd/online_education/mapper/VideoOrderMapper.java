package com.gaokd.online_education.mapper;

import com.gaokd.online_education.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoOrderMapper {
    /**
     * 查询用户是否购买过此商品
     * @param userId
     * @param videoId
     * @param state
     * @return
     */
    VideoOrder findByUserIdAndVideoAndState(@Param("user_id") int userId,@Param("video_id") int videoId,@Param("state") int state);

    /**
     * 下单
     * @param videoOrder
     * @return
     */
    int saveOrder(VideoOrder videoOrder);

    List<VideoOrder> orderListByUserId(@Param("user_id") Integer userId);
}
