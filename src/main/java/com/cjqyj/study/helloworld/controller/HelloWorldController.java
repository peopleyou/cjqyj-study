package com.cjqyj.study.helloworld.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yuanyouz on 2016/4/18.
 */
@Controller
public class HelloWorldController  {

    public static final String MODULE_NAME = "/helloworld";
    public static final String VIEW_NAME = "/helloworld";

    @RequestMapping(MODULE_NAME + "/to-hello-world")
    public ModelAndView toHelloWorld() {
        return new ModelAndView(VIEW_NAME + "/hello-world");
    }

    @ResponseBody
    @RequestMapping(MODULE_NAME + "/get-hello-world")
    public JSONObject getHelloWorld() {
        JSONObject result = new JSONObject();

        result.put("result", "hello world!!!");

        return result;
    }
}
