package com.onemt.view.domain;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/21
 */
public class ArticleData {
    //id,mediaCategoryId,mediaId,sourceUrl,publishTime,fetchTime,createTime
    private long id;
    private int categoryId;
    private int mediaId;
    private String sourceUrl;
    private int publishTime;
    private int fetchTime;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(int publishTime) {
        this.publishTime = publishTime;
    }

    public int getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(int fetchTime) {
        this.fetchTime = fetchTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return "article[id:" + id + ", categoryId:" + categoryId + ", mediaId:" +
                mediaId + ", sourceUrl:" + sourceUrl + ", publishTime:" + publishTime +
                ", fetchTime:" + fetchTime + ", createTime:" + createTime + "]";
    }
}
