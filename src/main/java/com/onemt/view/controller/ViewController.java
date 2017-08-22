package com.onemt.view.controller;

import com.onemt.view.domain.ArticleData;
import com.onemt.view.mapper.ArticleMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/21
 */

@Controller
@RequestMapping("/view")
public class ViewController {
    private Logger logger = LogManager.getLogger(ViewController.class);
    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        ArticleData article =  articleMapper.selectArticleById(869618391196224L);
        logger.info("get Article " + article.getId() + " " + article.getMediaName() + " " + article.getContentUrl());
        return article.getMediaName();
    }
}
