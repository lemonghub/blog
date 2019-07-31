package com.xmgl.blog.controller.fore.article;

import com.xmgl.blog.entity.Article;
import com.xmgl.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linbin
 * on 2019/4/24 21:33
 */
@RestController
@RequestMapping("forearticle")
public class ArticleForeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Marker marker = MarkerFactory.getMarker("foreArticle");

    @Autowired
    private ArticleService articleService;

    @PostMapping("allArticle")
    @ResponseBody
    private List<Article> getArticleAll(){
        List<Article> list = new ArrayList<>();
        list = articleService.selectAllArticle();
        if (list != null){
            logger.info(marker,
                    "{} | {}",
                    "article search information",
                    "article success");
        }
        return list;
    }

}
