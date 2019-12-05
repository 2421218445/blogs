package com.ffy.blog.controller;

import com.ffy.blog.po.Blog;
import com.ffy.blog.po.Comment;
import com.ffy.blog.po.User;
import com.ffy.blog.service.BlogCommentService;
import com.ffy.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class BlogCommentController {

    @Autowired
    private BlogCommentService blogCommentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{id}")
    public String comments(@PathVariable Long id, Model model) {
        model.addAttribute("comment", blogCommentService.listCommentByBlogId(id));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, @RequestParam(value = "content") String content, HttpSession session) {
        //把评论放进bean comment
        comment.setComment(content);
        //获得博客id
        Long id = comment.getBlog().getId();
        User user = (User) session.getAttribute("user");
        //判断是否是博主登录
        if (user != null) {
            comment.setAdminComment(true);
            comment.setAvatar(user.getAvatar());
        } else {
            //设置头像
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        //设置博客信息
        Blog blog = blogService.findBlogId(id);
        comment.setBlog(blog);
        blogCommentService.saveComment(comment);
        return "redirect:/comments/" + id;
    }


}
