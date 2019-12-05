package com.ffy.blog.controller;

import com.ffy.blog.po.Blog;
import com.ffy.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archives" , blogService.archivesBlog());
        model.addAttribute("count" , blogService.countBlog());
        return "archives";
    }
}
