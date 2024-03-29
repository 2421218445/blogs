package com.ffy.blog.dao;


import com.ffy.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long> {

    Integer deleteTypeById(Long id);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
