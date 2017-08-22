package com.onemt.view.service;

import com.onemt.view.domain.ArticleData;

import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/22
 */
public interface ArticleService {
    ArticleData getAritcleById(Long id);
    List<ArticleData> getArticleByMediaId(Integer mediaId,Long timestamp);
    List<ArticleData> getArticleByCategoryId(Integer categoryId,Long timestamp);
    List<ArticleData> getArticleByUrlContain(String containString,Long timestamp);
}
