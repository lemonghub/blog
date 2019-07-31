package com.xmgl.blog.controller.back.tag;

import com.xmgl.blog.entity.Tag;
import com.xmgl.blog.service.TagService;
import com.xmgl.blog.util.Result;
import com.xmgl.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 郭秦平
 * @data: 2019/04/14
 **/

@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("list")
    @ResponseBody
    private Result<List<Tag>> getTagList(String tagName, Integer page, Integer limit){
        System.out.println(1);
        return tagService.selectTagList(tagName, page, limit);
    }

    @PostMapping("add")
    @ResponseBody
    private ServerResponse addTag(@RequestBody Map<String,Object> map){
        tagService.insertTag((String)map.get("tagName"));
        return ServerResponse.createBySuccess();
    }

    @PostMapping("delete")
    @ResponseBody
    private ServerResponse deleteTag(@RequestBody Map<String,Object> map){
        tagService.deleteTag((Integer)map.get("tagId"));
        return ServerResponse.createBySuccess();
    }

    @PostMapping("update")
    @ResponseBody
    private ServerResponse updateTag(@RequestBody Map<String,Object> map){
        Tag tag = new Tag();
        try {
            int tagId = (Integer)map.get("tagId");
            String tagName = (String)map.get("tagName");
            tag.setTagId(tagId);
            tag.setTagName(tagName);
        }catch (Exception e){
            return ServerResponse.createByFailure(e.getMessage());
        }
        tagService.updateTag(tag);
        return ServerResponse.createBySuccess();
    }

    @PostMapping("listAll")
    @ResponseBody
    private List<Tag> getTagListAll(){
        return tagService.selectTagAll();
    }
}
