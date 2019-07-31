package com.xmgl.blog.dao;

import com.xmgl.blog.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleTagMapper {
    int insert(ArticleTag record);

    int insertSelective(ArticleTag record);

    int insertArticleTagList(@Param("list") List<ArticleTag> articleTagList);

    List<ArticleTag> selectByArticleId(int articleId);

    int deleteByArticleId(int articleId);
}