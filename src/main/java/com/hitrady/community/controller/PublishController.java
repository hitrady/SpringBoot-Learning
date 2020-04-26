package com.hitrady.community.controller;

import com.hitrady.community.mapper.QuestionMapper;
import com.hitrady.community.mapper.UserMapper;
import com.hitrady.community.model.Question;
import com.hitrady.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;

    @Autowired
    public PublishController(QuestionMapper questionMapper, UserMapper userMapper) {
        this.questionMapper = questionMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title") String title,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "tag") String tag,
                            HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        User user = new User();
        if (cookies == null) {
            return "index";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);

                    Question question = new Question();
                    question.setTitle(title);
                    question.setDescription(description);
                    question.setTag(tag);
                    question.setCreator(user.getId());
                    questionMapper.create(question);
                }
                break;
            }
        }
        if (user == null){
            return "index";
        }

        return "publish";
    }
}
