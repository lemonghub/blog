package com.xmgl.blog.dao;

import com.xmgl.blog.entity.Article;
import com.xmgl.blog.model.ArticleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> selectAllArticle();

    List<ArticleModel> selectArticleByPager(@Param("title") String title,@Param("categoryId") Integer categoryId, @Param("start") int start, @Param("limit") int limit);

    int selectArticleListCount(@Param("title") String title);

    int deleteArticleById(List<ArticleModel> list);

}