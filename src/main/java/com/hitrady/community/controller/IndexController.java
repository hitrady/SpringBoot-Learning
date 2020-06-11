package com.hitrady.community.controller;

import com.hitrady.community.dto.QuestionDTO;
import com.hitrady.community.mapper.UserMapper;
import com.hitrady.community.model.User;
import com.hitrady.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    private final UserMapper userMapper;
    private final QuestionService questionService;
    @Autowired
    public IndexController(UserMapper userMapper, QuestionService questionService) {
        this.userMapper = userMapper;
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "index";
        }
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questions",questionDTOList);
        return "index";
    }
}
