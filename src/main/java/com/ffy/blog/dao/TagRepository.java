package com.ffy.blog.dao;

import com.ffy.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Integer deleteTypeById(Long id);

    List<Tag> findTagByIdIn(List<Long> list);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

}
