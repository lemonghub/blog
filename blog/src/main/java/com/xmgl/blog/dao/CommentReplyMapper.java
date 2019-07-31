package com.xmgl.blog.dao;

import com.xmgl.blog.entity.CommentReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentReplyMapper {
    int insert(CommentReply record);

    int insertSelective(CommentReply record);
}