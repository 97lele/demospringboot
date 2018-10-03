package com.csdn.demospringboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class WebSocketController {
    @RequestMapping("/testwebsocket")
    public String testWebscoket(){
        return "test";
    }
    @RequestMapping("/testpath")
    public void testpath(){
        System.out.print("testpath");
    }
}
