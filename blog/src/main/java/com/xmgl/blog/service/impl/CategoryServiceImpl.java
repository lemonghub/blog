package com.xmgl.blog.service.impl;

import com.xmgl.blog.dao.CategoryMapper;
import com.xmgl.blog.entity.Category;
import com.xmgl.blog.service.CategoryService;
import com.xmgl.blog.util.PageCalculator;
import com.xmgl.blog.util.Result;
import com.xmgl.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 郭秦平
 * @data: 2019/04/14
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result<List<Category>> selectcategoryList(String categoryName, int page, int limit) {
        page = PageCalculator.checkPage(page);
        limit = PageCalculator.checkLimit(limit);
        int start = PageCalculator.calculateStart(page, limit);
        List<Category> customers = categoryMapper.selectCategoryByPager(categoryName, start, limit);
        int count = categoryMapper.selectCategoryListCount(categoryName);
        return Result.createSuccessResult(count, customers);
    }

    @Override
    public ServerResponse insertcategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryMapper.insertSelective(category);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse deletecategory(Integer categoryId) {
        categoryMapper.deleteByPrimaryKey(categoryId);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse updatecategory(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
        return ServerResponse.createBySuccess();
    }

    @Override
    public List<Category> selectCategoryListAll() {
        return categoryMapper.selectCategoryList();
    }
}
