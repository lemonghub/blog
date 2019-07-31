package com.xmgl.blog.service;

import com.xmgl.blog.entity.Article;
import com.xmgl.blog.entity.ArticleTag;
import com.xmgl.blog.model.ArticleModel;
import com.xmgl.blog.util.Result;
import com.xmgl.blog.util.ServerResponse;

import java.util.List;

/**
 * @author 郭秦平
 * @data: 2019/04/15
 **/
public interface ArticleService {

    /**
     * 文章列表
     * @param title
     * @param page
     * @param limit
     * @return
     */
    Result<List<ArticleModel>> selectArticleList(String title, Integer categoryId,int page, int limit);

    /**
     * 删除文章
     * @param articles
     * @return
     */
    ServerResponse deleteArticleById(List<ArticleModel> articles);

    /**
     * 插入文章
     * @param article
     * @return
     */
    ServerResponse insertArticle(Article article,List<ArticleTag> articleTags);

    /**
     * 查找文章ById
     * @param articleId
     * @return
     */
    Article selectArticleById(Integer articleId);

    /**
     * 查找全部文章
     * @return
     */
    List<Article> selectAllArticle();

    /**
     * 更新文章
     * @param article
     * @return
     */
    ServerResponse updateArticle(Article article,List<ArticleTag> articleTags);

    /**
     * 增加文章标签
     * @param articleTags
     * @return
     */
    ServerResponse insertArticleTag(List<ArticleTag> articleTags);

    /**
     * 查找文章标签
     * @param articleId
     * @return
     */
    List<ArticleTag> selectTagByArticleId(int articleId);

    /**
     * 是否置顶文章
     * @param article
     * @return
     */
    ServerResponse updateArticleTop(Article article);
}
