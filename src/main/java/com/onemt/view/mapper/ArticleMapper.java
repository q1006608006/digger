package com.onemt.view.mapper;

import com.onemt.view.domain.ArticleData;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/21
 */
public interface ArticleMapper {
    ArticleData selectArticleById(Long id);
}
