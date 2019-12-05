package com.ffy.blog.controller.admin;

import com.ffy.blog.po.Tag;
import com.ffy.blog.po.Type;
import com.ffy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tag")
public class TagsManageController {

    @Autowired
    private TagService tagServicel;

    /*
        跳转到标签页面
     */
    @RequestMapping("/tags")
    public String index(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable, Model model) {
        Page<Tag> tag = tagServicel.getListTag(pageable);
        model.addAttribute("page", tag);
        return "admin/tags";
    }

    /*
        添加标签
     */
    @RequestMapping("/saveTag")
    public String saveTag(Tag tag, RedirectAttributes attributes) {
        Tag t = tagServicel.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "标签添加失败");
        } else {
            attributes.addFlashAttribute("message", "标签添加成功");
        }
        return "redirect:/tag/tags";
    }
    /*

        标签删除
    */
    @GetMapping("/delTag")
    public String delType(@RequestParam(value = "id") Integer id , RedirectAttributes attributes) {
        Integer i = tagServicel.deleteTag(id.longValue());
        if (i == null) {
            attributes.addFlashAttribute("message", "标签删除失败！");
        } else {
            attributes.addFlashAttribute("message", "标签删除成功！");
        }
        return "redirect:/tag/tags";
    }
    /*
        数据查询
     */
    @ResponseBody
    @PostMapping("/idTag")
    public Tag fineIdType(Long id){
        Tag tag = tagServicel.getTagId(id);
        return tag;
    }
    /*
        分类修改
     */
    @PostMapping("/upTag")
    public String fineIdType(@RequestParam(value = "id") Long id , @RequestParam(value = "name") String name , RedirectAttributes attributes){
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        Tag t = tagServicel.UpdataTag(id, tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "标签修改失败！");
        } else {
            attributes.addFlashAttribute("message", "标签修改成功！");
        }
        return "redirect:/tag/tags";
    }

}
