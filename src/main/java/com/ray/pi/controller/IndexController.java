package com.ray.pi.controller;

import com.ray.pi.listener.CarEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ray on 2017/10/22.
 */
@Controller
public class IndexController {

    @Autowired
    private CarEventPublisher carPublisher;


    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/listen")
    public void publish() {
        carPublisher.pushListener("Engine Start");
    }
}
