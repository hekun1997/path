package coderxz.uestc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @RequestMapping("/page")
    public String pageIndex(){
        return "main";
    }
    @RequestMapping("/example")
    @ResponseBody
    public String example(){
        return "测试！！！";
    }
    @RequestMapping("/dist")
    public String dist(){
        return "orders-page-list";
    }
}
