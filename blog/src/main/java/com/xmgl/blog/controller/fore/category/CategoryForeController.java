package com.xmgl.blog.controller.fore.category;

import com.xmgl.blog.entity.Category;
import com.xmgl.blog.service.CategoryService;
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
 * on 2019/4/21 22:37
 */
@RestController
@RequestMapping("category")
public class CategoryForeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Marker marker = MarkerFactory.getMarker("category");

    @Autowired
    private CategoryService categoryService;

    @PostMapping("allcategory")
    @ResponseBody
    private List<Category> getCategoryAll(){
        List<Category> list = new ArrayList<>();
        list = categoryService.selectCategoryListAll();
        if (list != null){
            logger.info(marker,
                    "{} | {}",
                    "category search information",
                    "category success");
        }
        return list;
    }
}
