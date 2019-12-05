package com.ffy.blog.controller.admin;

import com.ffy.blog.po.Type;
import com.ffy.blog.service.TypeService;
import com.ffy.blog.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.dc.pr.PRError;

/**
 * 分类
 */
@Controller
@RequestMapping("/type")
public class TypeManageController {

    @Autowired
    private TypeService typeService;

    /*
        跳转 分类页面
     */
    @GetMapping("/types")
    public String index(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable, Model model) {
        model.addAttribute("page", typeService.getListPage(pageable));
        return "admin/types";
    }
    /*
        数据查询
     */
    @ResponseBody
    @PostMapping("/idType")
    public Type fineIdType(Long id){
        Type typeId = typeService.getTypeId(id);
        return typeId;
    }
    /*
        分类修改
     */
    @PostMapping("/upType")
    public String fineIdType(@RequestParam(value = "id") Long id , @RequestParam(value = "name") String name , RedirectAttributes attributes){
        Type type = new Type();
        type.setId(id);
        type.setName(name);
        Type t = typeService.updateType(id, type);
        if (t == null) {
            attributes.addFlashAttribute("message", "分类修改失败！");
        } else {
            attributes.addFlashAttribute("message", "分类修改成功！");
        }
        return "redirect:/type/types";
    }

    /*
        分类添加
     */
    @PostMapping("/saveType")
    public String saveType(Type type, RedirectAttributes attributes) {
        System.out.println(type);
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "分类添加失败！");
        } else {
            attributes.addFlashAttribute("message", "分类添加成功！");
        }
        return "redirect:/type/types";
    }
    /*
        分类删除
     */
    @GetMapping("/delType")
    public String delType(@RequestParam(value = "id") Integer id , RedirectAttributes attributes) {
        Integer i = typeService.deleteType(id.longValue());
        if (i == null) {
            attributes.addFlashAttribute("message", "分类删除失败！");
        } else {
            attributes.addFlashAttribute("message", "分类删除成功！");
        }
        return "redirect:/type/types";
    }

}
