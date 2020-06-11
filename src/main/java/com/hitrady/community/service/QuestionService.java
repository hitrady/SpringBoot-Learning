package com.hitrady.community.service;

import com.hitrady.community.dto.QuestionDTO;
import com.hitrady.community.mapper.QuestionMapper;
import com.hitrady.community.mapper.UserMapper;
import com.hitrady.community.model.Question;
import com.hitrady.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final UserMapper userMapper;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(UserMapper userMapper, QuestionMapper questionMapper) {
        this.userMapper = userMapper;
        this.questionMapper = questionMapper;
    }

    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question:questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findById(question.getCreator());
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
