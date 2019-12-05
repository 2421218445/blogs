package com.ffy.blog.dao;

import com.ffy.blog.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogIssueRepository extends JpaRepository<Blog,Long> {
}
