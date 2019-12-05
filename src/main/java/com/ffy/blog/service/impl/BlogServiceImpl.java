package com.ffy.blog.service.impl;

import com.ffy.blog.dao.BlogRepository;
import com.ffy.blog.exception.NotFundException;
import com.ffy.blog.po.Blog;
import com.ffy.blog.po.Type;
import com.ffy.blog.service.BlogService;
import com.ffy.blog.util.MarkdownUtils;
import com.ffy.blog.util.MyBeanUtils;
import com.ffy.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog findBlogId(Long id) {
        Blog one = blogRepository.getOne(id);
        if (one == null){
            throw new NotFundException("博客信息为空！");
        }
        return one;
    }

    @Transactional
    @Override
    public Blog findBlogContentId(Long id) {
        Blog blog = blogRepository.getOne(id);
        if (blog == null){
            throw new NotFundException("博客信息为空！");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog , b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<Blog> findListBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            /*
                条件查询
             */
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommendStatus"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> findListBlog(Pageable pageable, String query) {
        return blogRepository.findByQuery(pageable,query);
    }

    @Override
    public Page<Blog> findListBlog(Pageable pageable, Long tagId) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id") , tagId);
            }
        } , pageable);
    }

    @Override
    public Page<Blog> findListBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Map<String, List<Blog>> archivesBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String , List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year,blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    @Transactional
    public Integer deleteBlogId(Long id) {
        return blogRepository.deleteBlogById(id);
    }

    @Override
    @Transactional
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b == null) {
            throw new NotFundException("博客信息不存在..");
        }
        /*
            blog 源对象
            b 目标对象
            把源对象拷贝到目标对象
         */
        BeanUtils.copyProperties(blog, b , MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort.Order sort = new Sort.Order(Sort.Direction.DESC , "updateTime");
        Pageable pageable = PageRequest.of(0,size,Sort.by(sort));
        return blogRepository.findTop(pageable);
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }
}
