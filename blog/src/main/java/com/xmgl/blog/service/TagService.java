package com.xmgl.blog.service;

import com.xmgl.blog.entity.Tag;
import com.xmgl.blog.util.Result;
import com.xmgl.blog.util.ServerResponse;

import java.util.List;

/**
 * @author 郭秦平
 * @data: 2019/04/14
 **/
public interface TagService {

    /**
     * 查询标签列表
     * @param tagName
     * @param page
     * @param limit
     * @return
     */
    Result<List<Tag>> selectTagList(String tagName, int page, int limit);

    /**
     * 添加标签
     * @param tagName
     * @return
     */
    ServerResponse insertTag(String tagName);

    /**
     * 删除标签
     * @param tagId
     * @return
     */
    ServerResponse deleteTag(Integer tagId);

    /**
     * 更新标签
     * @param tag
     * @return
     */
    ServerResponse updateTag(Tag tag);

    List<Tag> selectTagAll();
}
