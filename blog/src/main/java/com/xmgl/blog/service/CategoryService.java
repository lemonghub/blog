package com.xmgl.blog.service;

import com.xmgl.blog.entity.Category;
import com.xmgl.blog.util.Result;
import com.xmgl.blog.util.ServerResponse;

import java.util.List;

/**
 * @author 郭秦平
 * @data: 2019/04/14
 **/
public interface CategoryService {

    /**
     * 查询分类列表
     * @param categoryName
     * @param page
     * @param limit
     * @return
     */
    Result<List<Category>> selectcategoryList(String categoryName, int page, int limit);

    /**
     * 添加分类
     * @param categoryName
     * @return
     */
    ServerResponse insertcategory(String categoryName);

    /**
     * 删除分类
     * @param categoryId
     * @return
     */
    ServerResponse deletecategory(Integer categoryId);

    /**
     * 更新分类
     * @param category
     * @return
     */
    ServerResponse updatecategory(Category category);

    List<Category> selectCategoryListAll();
}
