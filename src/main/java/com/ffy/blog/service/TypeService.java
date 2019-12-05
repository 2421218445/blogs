package com.ffy.blog.service;

import com.ffy.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type saveType(Type type);

    Type getTypeId(Long id);

    Page<Type> getListPage(Pageable pageable);

    Type updateType(Long id , Type type);

    Integer deleteType(Long id);

    List<Type> findListType();

    List<Type> findListType(Integer size);

}
