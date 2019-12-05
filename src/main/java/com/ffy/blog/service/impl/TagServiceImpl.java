package com.ffy.blog.service.impl;

import com.ffy.blog.dao.TagRepository;
import com.ffy.blog.exception.NotFundException;
import com.ffy.blog.po.Tag;
import com.ffy.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag getTagId(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    @Transactional
    public Tag UpdataTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if (t == null) {
            throw new NotFundException("类型不存在...");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Override
    @Transactional
    public Page<Tag> getListTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> findListTag(String ids) {
        return tagRepository.findTagByIdIn(convertToList(ids));
    }
    /*
        字符串转换成Long集合
     */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] split = ids.split(",");
            for (int i = 0 ; i < split.length ; i++){
                list.add(new Long(split[i]));
            }
        }
        return list;
    }

    @Override
    @Transactional
    public Integer deleteTag(Long id) {
        return tagRepository.deleteTypeById(id);
    }

    @Override
    public List<Tag> findListTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> findListTag(Integer size) {
        Sort.Order sort = new Sort.Order(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0, size,Sort.by(sort));
        return tagRepository.findTop(pageable);
    }
}
