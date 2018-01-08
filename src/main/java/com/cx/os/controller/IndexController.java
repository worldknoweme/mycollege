package com.cx.os.controller;

import com.cx.os.model.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(ModelMap model, HttpServletRequest request){
        return "index";
    }

//    @RequestMapping(produces = "text/plain;charset=utf-8")
//    public @ResponseBody String main(HttpServletRequest request){
//        return "url:"+request.getRequestURI()+" can access";
//    }
//
//    /**
//     * url传参
//     * @param str
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=utf8")
//    public @ResponseBody String demoPathVar(@PathVariable String str,HttpServletRequest request){
//        return "url:"+request.getRequestURI()+" can assess , str :"+str;
//    }
//
//    /**
//     * uri传参，传入指定的属性值
//     * @param id
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/requestParam",produces = "text/plain;charset=utf8")
//    public @ResponseBody String passRequestParam(Long id,HttpServletRequest request){
//        return "url:"+request.getRequestURI()+" can assess,id:"+id;
//    }
//
//    /**
//     * url传参，传入一个对象
//     * @param obj
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/obj",produces = "application/json;charset=utf8")
//    public @ResponseBody String passobj(DemoObj obj,HttpServletRequest request){
//        return "url:"+request.getRequestURI()+" can assess,objid="+obj.getId()+" objname="+obj.getName();
//    }
//
//    /**
//     * 多个controller指向同一方法
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = {"/name1","name2"},produces = "text/plain;charset=utf8")
//    public @ResponseBody String multi(HttpServletRequest request){
//        return "url:"+request.getRequestURI()+" can assess";
//    }
}
