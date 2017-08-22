package com.onemt.view.service;

import com.onemt.view.domain.ArticleData;
import com.onemt.view.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/22
 */

public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public ArticleData getAritcleById(Long id) {
        return articleMapper.selectArticleById(id);
    }

    public List<ArticleData> getArticleByMediaId(Integer mediaId, Long timestamp) {
        return null;
    }

    public List<ArticleData> getArticleByCategoryId(Integer categoryId, Long timestamp) {
        return null;
    }

    public List<ArticleData> getArticleByUrlContain(String containString, Long timestamp) {
        return null;
    }
}
