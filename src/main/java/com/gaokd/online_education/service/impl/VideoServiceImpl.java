package com.gaokd.online_education.service.impl;

import com.gaokd.online_education.domain.Video;
import com.gaokd.online_education.mapper.VideoMapper;
import com.gaokd.online_education.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> videoList() {
        return videoMapper.videoList();
    }
}