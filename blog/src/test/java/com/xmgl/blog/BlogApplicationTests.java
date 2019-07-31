package com.xmgl.blog;

import com.xmgl.blog.entity.Tag;
import com.xmgl.blog.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private TagService tagService;

    @Test
    public void contextLoads() {
        List<Tag> tags = tagService.selectTagAll();
        for(Tag e : tags){
            System.out.println(e.getTagName());
        }
    }

}
