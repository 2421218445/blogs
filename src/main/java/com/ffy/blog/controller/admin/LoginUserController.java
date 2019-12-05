package com.ffy.blog.controller.admin;

import com.ffy.blog.po.User;
import com.ffy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

/**
 * 登录
 */
@Controller
@RequestMapping("/admin")
public class LoginUserController {

    @Autowired
    private UserService userService;
    //登录页面
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }
    //后台首页
    @GetMapping("/index")
    public String main(){
        return "admin/index";
    }
    //登录
    @RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
    public String LoginIndex(@RequestParam(value = "username" ,required = false) String username ,
                             @RequestParam(value = "password" ,required = false) String password ,
                             HttpSession session ,
                             RedirectAttributes attributes){
        if (username == null || password == null){
            attributes.addFlashAttribute("message","请不要直接访问，我是有拦截的！");
            return "redirect:/admin/login";
        }
        User user = userService.findUserLogin(username, password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/admin/index";
        }else {
            attributes.addFlashAttribute("message","密码或账户错误！");
            return "redirect:/admin/login";
        }
    }
    //注销
    @GetMapping("/logout")
    public String LogoutUser(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }

}
