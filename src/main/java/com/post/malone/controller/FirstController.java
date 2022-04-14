package com.post.malone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String wonderwall(Model model) {
        model.addAttribute("username","OASIS");
        return "supersonic";
    }

    @GetMapping("/parklife")
    public String beetlebum(Model model) {
        model.addAttribute("nickname","BLUR");
        return "stereotypes";
    }
}
