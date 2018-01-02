package com.cx.os.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(ModelMap model, HttpServletRequest request){
        return "index";
    }
}
