package com.hitrady.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model){
        if(action.equals("questions")){
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我的问题");
        }else if(action.equals("reply")){
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我的回复");
        }
        return "profile";
    }
}