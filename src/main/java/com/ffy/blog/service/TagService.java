package com.ffy.blog.service;

import com.ffy.blog.po.Tag;
import com.ffy.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTagId(Long id);

    Tag UpdataTag(Long id ,Tag tag);

    Page<Tag> getListTag(Pageable pageable);

    List<Tag> findListTag(String ids);

    Integer deleteTag(Long id);

    List<Tag> findListTag();

    List<Tag> findListTag(Integer size);
}
