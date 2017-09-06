package com.onemt.view.controller;

import com.gargoylesoftware.htmlunit.WebClient;
import com.onemt.view.bridge.ArticleCondition;
import com.onemt.view.mapper.ArticleMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

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
        logger.info(articleMapper.selectArticleById(875908436619840L));
        ArticleCondition condition = new ArticleCondition();
//        condition.addMedia(54);
//        condition.addMedia(611);
        condition.addCategory(611);

        condition.setFetchTime(1502679806);
        condition.setEndFetchTime(1502679816);


        condition.setOrderBy("publishTime");

        Jedis jedis = new Jedis();



        logger.info(articleMapper.selectArticleByBridge(condition));
        return "你好";
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

    @RequestMapping(value = "/json")
    @ResponseBody
    public Map<String,String> getJson() {
        Map<String,String> ret = new HashMap<String, String>();
        ret.put("hello","ivan");
        return ret;
    }
}
