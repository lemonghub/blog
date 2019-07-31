package com.xmgl.blog.dao;

import com.xmgl.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectCategoryByPager(@Param("categoryName") String categoryName, @Param("start") int start, @Param("limit") int limit);

    int selectCategoryListCount(@Param("categoryName") String categoryName);

    List<Category> selectCategoryList();
}