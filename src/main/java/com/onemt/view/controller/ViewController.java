package com.onemt.view.controller;

import com.onemt.view.domain.ArticleCondition;
import com.onemt.view.domain.ArticleData;
import com.onemt.view.mapper.ArticleMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        ArticleData article =  articleMapper.selectArticleById(869618370531904L);
        ArticleCondition condition = new ArticleCondition();
        condition.setMediaId(113);
        condition.setFetchTime(1501144153);

        List<ArticleData> articleDataList = articleMapper.selectArticleByMediaId(condition);
        return article.getMediaName();
//        return "你好";
    }


    @RequestMapping("/view-input")
    public String viewInput() {
        return "/jsp/input.jsp";
    }

    @RequestMapping("/input")
    @ResponseBody
    public String input(String param) {
        logger.info("receive parameter " + param);
        return param;
    }
}
