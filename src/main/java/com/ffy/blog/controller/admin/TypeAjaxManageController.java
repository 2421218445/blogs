package com.ffy.blog.controller.admin;

import com.ffy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/typeAjax")
public class TypeAjaxManageController {

    @Autowired
    private TypeService typeService;

    @ResponseBody
    @GetMapping("/types")
    public String index(@PageableDefault(size = 10 , sort = "id" , direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page", typeService.getListPage(pageable));
        return "admin/types";
    }

}
