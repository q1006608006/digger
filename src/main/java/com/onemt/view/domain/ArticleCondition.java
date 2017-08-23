package com.onemt.view.domain;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/23
 */
public class ArticleCondition {
    private int limit;
    private int offset;
    private int mediaId;
    private int categoryId;
    private long fetchTime;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public long getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(long timestamp) {
        this.fetchTime = timestamp;
    }
}
