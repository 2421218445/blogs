package com.ffy.blog.controller.admin;

import com.ffy.blog.po.Blog;
import com.ffy.blog.po.User;
import com.ffy.blog.service.BlogService;
import com.ffy.blog.service.TagService;
import com.ffy.blog.service.TypeService;
import com.ffy.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/blog")
public class BlogManageController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /*
        分类 ， 标签
     */
    private void TypeTag(Model model) {
        model.addAttribute("type", typeService.findListType());
        model.addAttribute("tag", tagService.findListTag());
    }

    /*
        跳转到博客列表
     */
    @RequestMapping("/blogs")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"} , direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model) {
        Page<Blog> page = blogService.findListBlog(pageable, blog);
        TypeTag(model);
        model.addAttribute("page", page);
        return "admin/blogs";
    }

    /*
        动态刷新
     */
    @RequestMapping("/blogs/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}) Pageable pageable, BlogQuery blog, Model model) {
        Page<Blog> page = blogService.findListBlog(pageable, blog);
        model.addAttribute("page", page);
        return "admin/blogs :: blogList";
    }

    /*
        修改博客
     */
    @PostMapping("/updateBlog")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeId(blog.getType().getId()));
        blog.setTags(tagService.findListTag(blog.getTagIds()));
        Blog b = blogService.updateBlog(blog.getId(), blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "博客修改失败！");
        } else {
            attributes.addFlashAttribute("message", "博客修改成功！");
        }
        return "redirect:/blog/blogs";
    }

    /*
        跳转到修改博客页面
     */
    @RequestMapping("/upBlog")
    public String upBlog(Long id, Model model) {
        Blog blog = blogService.findBlogId(id);
        blog.init();
        model.addAttribute("blog", blog);
        TypeTag(model);
        return "/admin/blogs-update";
    }

    /*
        删除博客
     */
    @RequestMapping("/delBlog")
    public String delBlog(Long id, RedirectAttributes attributes) {
        Integer integer = blogService.deleteBlogId(id);
        if (integer != null) {
            attributes.addFlashAttribute("message", "博客删除成功！");
        } else {
            attributes.addFlashAttribute("message", "博客删除失败！");
        }
        return "redirect:/blog/blogs";
    }

}
