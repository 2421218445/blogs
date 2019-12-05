package com.ffy.blog.service.impl;

import com.ffy.blog.dao.TypeRepository;
import com.ffy.blog.exception.NotFundException;
import com.ffy.blog.po.Type;
import com.ffy.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getTypeId(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> getListPage(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        if (t == null){
            throw new NotFundException("类型不存在...");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public Integer deleteType(Long id) {
        return typeRepository.deleteTypeById(id);
    }

    @Override
    public List<Type> findListType() {
        return typeRepository.findAll();
    }

    /*
        定义查找，查找由数量大到小
     */
    @Override
    public List<Type> findListType(Integer size) {
        Sort.Order sort = new Sort.Order(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0, size,Sort.by(sort));
        return typeRepository.findTop(pageable);
    }
}
