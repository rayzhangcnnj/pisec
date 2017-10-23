package com.ray.pi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ray on 2017/10/22.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }
}
