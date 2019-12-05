package com.ffy.blog.service;

import com.ffy.blog.po.Comment;

import java.util.List;

public interface BlogCommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

}
