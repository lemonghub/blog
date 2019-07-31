package com.xmgl.blog.service.impl;

import com.xmgl.blog.dao.TagMapper;
import com.xmgl.blog.entity.Tag;
import com.xmgl.blog.service.TagService;
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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Result<List<Tag>> selectTagList(String tagName, int page, int limit) {
        page = PageCalculator.checkPage(page);
        limit = PageCalculator.checkLimit(limit);
        int start = PageCalculator.calculateStart(page, limit);
        List<Tag> customers = tagMapper.selectTagByPager(tagName, start, limit);
        int count = tagMapper.selectTagListCount(tagName);
        return Result.createSuccessResult(count, customers);
    }

    @Override
    public ServerResponse insertTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tagMapper.insertSelective(tag);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse deleteTag(Integer tagId) {
        tagMapper.deleteByPrimaryKey(tagId);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse updateTag(Tag tag) {
        tagMapper.updateByPrimaryKeySelective(tag);
        return ServerResponse.createBySuccess();
    }

    @Override
    public List<Tag> selectTagAll() {
        return tagMapper.selectTagList();
    }
}
