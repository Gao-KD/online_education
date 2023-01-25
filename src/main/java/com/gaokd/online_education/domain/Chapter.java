package com.gaokd.online_education.domain;

<<<<<<< HEAD
import java.util.Date;
=======
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
>>>>>>> origin/develop

/**
 * 章
 */
<<<<<<< HEAD
public class Chapter {
=======

public class Chapter implements Serializable {
>>>>>>> origin/develop
    
    private Integer id;
    
    private Integer videoId;
    
    private String title;
    
    private Integer ordered;
    
    private Date createTime;

<<<<<<< HEAD
=======
    private List<Episode> episodeList;

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

>>>>>>> origin/develop
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
<<<<<<< HEAD
=======


>>>>>>> origin/develop
}
