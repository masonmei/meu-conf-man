package com.baidu.oped.iop.m4.mvc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mason
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
