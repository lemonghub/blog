package com.xmgl.blog.dao;

import com.xmgl.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> selectTagByPager(@Param("tagName") String tagName, @Param("start") int start, @Param("limit") int limit);

    int selectTagListCount(@Param("tagName") String tagName);

    List<Tag> selectTagList();
}