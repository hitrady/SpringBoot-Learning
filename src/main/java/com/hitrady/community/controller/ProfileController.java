package com.hitrady.community.controller;

import com.hitrady.community.dto.PaginationDTO;
import com.hitrady.community.mapper.UserMapper;
import com.hitrady.community.model.User;
import com.hitrady.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    private final UserMapper userMapper;
    private final QuestionService questionService;

    public ProfileController(UserMapper userMapper, QuestionService questionService) {
        this.userMapper = userMapper;
        this.questionService = questionService;
    }

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "size",defaultValue = "5") Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "index";
        }
        User user = null;
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if(user==null){
            return "redirect:/";
        }
        if(action.equals("questions")){
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我的问题");
        }else if(action.equals("reply")){
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我的回复");
        }
        PaginationDTO pagination = questionService.list(user.getId(),page,size);
        model.addAttribute("pagination",pagination);
        return "profile";
    }
}
