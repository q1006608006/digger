package com.onemt.view.mapper;

import com.onemt.view.bridge.ArticleCondition;
import com.onemt.view.domain.ArticleData;

import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/21
 */
public interface ArticleMapper {
    ArticleData selectArticleById(Long id);
    List<ArticleData> selectArticleByBridge(ArticleCondition condition);
//    List<ArticleData> selectArticleByCategoryId(Integer categoryId,Long timestamp);
//    List<ArticleData> selectArticleByUrlContain(String contain,Long timestamp);
}
