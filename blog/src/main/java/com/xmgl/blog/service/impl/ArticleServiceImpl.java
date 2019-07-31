package com.xmgl.blog.service.impl;

import com.xmgl.blog.dao.ArticleMapper;
import com.xmgl.blog.dao.ArticleTagMapper;
import com.xmgl.blog.entity.Article;
import com.xmgl.blog.entity.ArticleTag;
import com.xmgl.blog.model.ArticleModel;
import com.xmgl.blog.service.ArticleService;
import com.xmgl.blog.util.PageCalculator;
import com.xmgl.blog.util.Result;
import com.xmgl.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭秦平
 * @data: 2019/04/15
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public Result<List<ArticleModel>> selectArticleList(String title, Integer categoryId,int page, int limit) {
        page = PageCalculator.checkPage(page);
        limit = PageCalculator.checkLimit(limit);
        int start = PageCalculator.calculateStart(page, limit);
        List<ArticleModel> customers = articleMapper.selectArticleByPager(title,categoryId, start, limit);
        int count = articleMapper.selectArticleListCount(title);
        return Result.createSuccessResult(count, customers);
    }

    @Override
    public List<Article> selectAllArticle() {
        List<Article> list = new ArrayList<>();
        list = articleMapper.selectAllArticle();
        return list;
    }

    @Override
    public ServerResponse deleteArticleById(List<ArticleModel> articles) {
        articleMapper.deleteArticleById(articles);
        for(ArticleModel articleModel:articles){
            articleTagMapper.deleteByArticleId(articleModel.getArticleId());
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse insertArticle(Article article,List<ArticleTag> articleTags) {
        articleMapper.insertSelective(article);
        if(articleTags.size()!=0) {
            for (ArticleTag articleTag : articleTags) {
                articleTag.setArticleId(article.getArticleId());
            }
            articleTagMapper.insertArticleTagList(articleTags);
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public Article selectArticleById(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public ServerResponse updateArticle(Article article ,List<ArticleTag> articleTags) {
        articleTagMapper.deleteByArticleId(article.getArticleId());
        if(articleTags.size()!=0) {
            for (ArticleTag articleTag : articleTags) {
                articleTag.setArticleId(article.getArticleId());
            }
            articleTagMapper.insertArticleTagList(articleTags);
        }
        articleMapper.updateByPrimaryKeySelective(article);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse insertArticleTag(List<ArticleTag> articleTags) {
        articleTagMapper.insertArticleTagList(articleTags);
        return ServerResponse.createBySuccess();
    }

    @Override
    public List<ArticleTag> selectTagByArticleId(int articleId) {
        return articleTagMapper.selectByArticleId(articleId);
    }

    @Override
    public ServerResponse updateArticleTop(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
        return ServerResponse.createBySuccess();
    }
}
