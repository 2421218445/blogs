package com.ffy.blog.controller;

import com.ffy.blog.po.Blog;
import com.ffy.blog.po.Type;
import com.ffy.blog.service.BlogService;
import com.ffy.blog.service.TypeService;
import com.ffy.blog.vo.BlogQuery;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @RequestMapping("/typeblog")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        List<Type> type = typeService.findListType(9999);
        BlogQuery blogQuery = new BlogQuery();
        if (id == -1) {
            id = type.get(0).getId();
        }
        blogQuery.setTypeId(id);
        Page<Blog> blog = blogService.findListBlog(pageable, blogQuery);
        model.addAttribute("page" , blog);
        model.addAttribute("types" , type);
        model.addAttribute("tid" , id);
        return "types";
    }

}
