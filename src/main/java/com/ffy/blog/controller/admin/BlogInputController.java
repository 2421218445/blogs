package com.ffy.blog.controller.admin;

import com.ffy.blog.po.Blog;
import com.ffy.blog.po.User;
import com.ffy.blog.service.TagService;
import com.ffy.blog.service.TypeService;
import com.ffy.blog.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/blogIssu")
public class BlogInputController {

    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/blogs")
    public String index(Model model){
        model.addAttribute("tag",tagService.findListTag());
        model.addAttribute("type",typeService.findListType());
        return "admin/blogs-input";
    }
    @PostMapping("/addBlog")
    public String addBlog(Blog blog , RedirectAttributes attributes , HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeId(blog.getType().getId()));
        blog.setTags(tagService.findListTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);
        if (b == null){
            attributes.addFlashAttribute("message","博客添加失败！");
        }else {
            attributes.addFlashAttribute("message","博客添加成功！");
        }
        return "redirect:/blog/blogs";
    }

}
