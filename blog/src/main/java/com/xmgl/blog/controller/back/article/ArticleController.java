package com.xmgl.blog.controller.back.article;

import com.alibaba.fastjson.JSONObject;
import com.xmgl.blog.entity.Article;
import com.xmgl.blog.entity.ArticleTag;
import com.xmgl.blog.entity.Category;
import com.xmgl.blog.model.ArticleModel;
import com.xmgl.blog.service.ArticleService;
import com.xmgl.blog.service.CategoryService;
import com.xmgl.blog.util.ObjectMapperUtil;
import com.xmgl.blog.util.Result;
import com.xmgl.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 郭秦平
 * @data: 2019/04/15
 **/

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("list")
    @ResponseBody
    private Result<List<ArticleModel>> getArticleList(String title,Integer categoryId, Integer page, Integer limit){
        return articleService.selectArticleList(title,categoryId, page, limit);
    }

    @PostMapping("delete")
    @ResponseBody
    private ServerResponse deleteArticle(@RequestBody Map<String,Object> map){
        System.out.println(11);
        try {
            String json = ObjectMapperUtil.objectToString(map.get("article"));
            System.out.println(json);
            List<ArticleModel> articles = ObjectMapperUtil.convertList(json,ArticleModel.class);
            articleService.deleteArticleById(articles);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByFailure(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }

    @PostMapping("add")
    @ResponseBody
    private ServerResponse addArticle(@RequestBody Map<String,Object> map){
        try{
            String tags = ObjectMapperUtil.objectToString(map.get("tags"));
            List<ArticleTag> articleTags = ObjectMapperUtil.convertList(tags,ArticleTag.class);
            String json = ObjectMapperUtil.objectToString(map.get("article"));
            Article article = ObjectMapperUtil.convertObj(json,Article.class);
            article.setCreateAt(new Date());
            articleService.insertArticle(article,articleTags);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByFailure(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }

    @PostMapping("toEdit")
    @ResponseBody
    private ServerResponse showEditArticle(@RequestBody Map<String,Object> map){
        try{
            Integer articleId = (int)map.get("articleId");
            Article article = articleService.selectArticleById(articleId);
            List<Category> categories = categoryService.selectCategoryListAll();
            List<ArticleTag> articleTags = articleService.selectTagByArticleId(articleId);
            JSONObject jo = new JSONObject();
            jo.put("article", article);
            jo.put("categoryList",categories);
            jo.put("tagList",articleTags);
            return ServerResponse.createBySuccess(jo);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByFailure(e.getMessage());
        }
    }

    @PostMapping("edit")
    @ResponseBody
    private ServerResponse editArticle(@RequestBody Map<String,Object> map){
        try{
            String tags = ObjectMapperUtil.objectToString(map.get("tags"));
            List<ArticleTag> articleTags = ObjectMapperUtil.convertList(tags,ArticleTag.class);
            String json = ObjectMapperUtil.objectToString(map.get("article"));
            Article article = ObjectMapperUtil.convertObj(json,Article.class);
            article.setUpdateAt(new Date());
            articleService.updateArticle(article,articleTags);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByFailure(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }

    @PostMapping("isTop")
    @ResponseBody
    private ServerResponse editArticleTop(@RequestBody Map<String,Object> map){
        try{
            String json = ObjectMapperUtil.objectToString(map.get("article"));
            Article article = ObjectMapperUtil.convertObj(json,Article.class);
            articleService.updateArticleTop(article);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByFailure(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }
}
