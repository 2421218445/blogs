package com.ffy.blog.controller;

import com.ffy.blog.po.Blog;
import com.ffy.blog.po.Type;
import com.ffy.blog.service.BlogService;
import com.ffy.blog.service.TagService;
import com.ffy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping(value = {"/" , "/index"})
    public String index(@PageableDefault(size = 10, sort = {"updateTime"} , direction = Sort.Direction.DESC) Pageable pageable , Model model) {
        Page<Blog> blog = blogService.findListBlog(pageable);
        model.addAttribute("page" , blog);
        model.addAttribute("type", typeService.findListType(6));
        model.addAttribute("tag", tagService.findListTag(6));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index.html";
    }
    //博客详情
    @GetMapping("/blogDetail")
    public String blog(Long id , Model model) {
        model.addAttribute("blog" , blogService.findBlogContentId(id));
        return "blog";
    }

    @GetMapping("/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"} , direction = Sort.Direction.DESC) Pageable pageable , String query ,  Model model) {
        model.addAttribute("page" , blogService.findListBlog(pageable , '%' + query + '%'));
        model.addAttribute("query" , query);
        return "search";
    }

    @RequestMapping("/footer/newblog")
    public String newblogList(Model model){
        model.addAttribute("newblogs"  ,blogService.listRecommendBlogTop(3));
        return "_fragment :: newblogList";
    }

}
