package com.onemt.view.mapper;

import com.onemt.view.domain.ArticleCondition;
import com.onemt.view.domain.ArticleData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/21
 */
public interface ArticleMapper {
    ArticleData selectArticleById(Long id);
    List<ArticleData> selectArticleByMediaId(ArticleCondition condition);
//    List<ArticleData> selectArticleByCategoryId(Integer categoryId,Long timestamp);
//    List<ArticleData> selectArticleByUrlContain(String contain,Long timestamp);
}
