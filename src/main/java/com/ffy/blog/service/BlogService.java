package com.ffy.blog.service;

import com.ffy.blog.po.Blog;
import com.ffy.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog saveBlog(Blog blog);

    Blog findBlogId(Long id);

    Blog findBlogContentId(Long id);

    Page<Blog> findListBlog(Pageable pageable , BlogQuery blog);

    Page<Blog> findListBlog(Pageable pageable , String query);

    Page<Blog> findListBlog(Pageable pageable , Long tagId);

    Page<Blog> findListBlog(Pageable pageable);

    Map<String , List<Blog>> archivesBlog();

    Integer deleteBlogId(Long id);
    Blog updateBlog(Long id , Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);

    Long countBlog();
}
