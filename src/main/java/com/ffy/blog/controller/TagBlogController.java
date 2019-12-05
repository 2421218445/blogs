package com.ffy.blog.controller;

import com.ffy.blog.po.Blog;
import com.ffy.blog.po.Tag;
import com.ffy.blog.service.BlogService;
import com.ffy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @RequestMapping("/tagblog")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        List<Tag> tag = tagService.findListTag(9999);
        if (id == -1) {
            id = tag.get(0).getId();
        }
        Page<Blog> blog = blogService.findListBlog(pageable, id);
        model.addAttribute("page" , blog);
        model.addAttribute("tags" , tag);
        model.addAttribute("tid" , id);
        return "tags";
    }
}
