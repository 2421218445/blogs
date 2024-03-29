package com.ffy.blog.dao;

import com.ffy.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog , Long> {

    Integer deleteBlogById(Long id);

    Page<Blog> findAll(Specification<Blog> blogSpecification, Pageable pageable);

    @Query("select b from Blog b where b.recommendStatus = true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from  Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(Pageable pageable , String query);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
