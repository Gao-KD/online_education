package com.gaokd.online_education.controller;

import com.gaokd.online_education.model.entity.VideoOrder;
import com.gaokd.online_education.model.request.VideoOrderRequest;
import com.gaokd.online_education.service.VideoOrderService;
import com.gaokd.online_education.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {
    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单接口
     * @return
     */
    @RequestMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        int rows = videoOrderService.save(userId,videoOrderRequest.getVideoId());
        return rows == 0?JsonData.buildError("下单失败"):JsonData.buildSucess();
    }

    @RequestMapping("list")
    public JsonData orderListByUserId(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrderList = videoOrderService.orderListByUserId(userId);
        return JsonData.buildSucess(videoOrderList);
    }

}

