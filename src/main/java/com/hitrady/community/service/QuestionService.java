package com.hitrady.community.service;

import com.hitrady.community.dto.PaginationDTO;
import com.hitrady.community.dto.QuestionDTO;
import com.hitrady.community.mapper.QuestionMapper;
import com.hitrady.community.mapper.UserMapper;
import com.hitrady.community.model.Question;
import com.hitrady.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalSize = questionMapper.count();
        PaginationDTO pagination = new PaginationDTO();
        pagination.setCurrentPage(totalSize, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > pagination.getTotalPage()) {
            page = pagination.getTotalPage();
        }
        Integer startCount = (page - 1) * size;
        List<Question> questionList = questionMapper.list(startCount, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findById(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);
        return pagination;
    }

    public PaginationDTO list(Integer id, Integer page, Integer size) {
        Integer totalSize = questionMapper.countByUserId(id);
        PaginationDTO pagination = new PaginationDTO();
        pagination.setCurrentPage(totalSize, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > pagination.getTotalPage()) {
            page = pagination.getTotalPage();
        }
        Integer startCount = (page - 1) * size;
        List<Question> questionList = questionMapper.listByUserId(id,startCount, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findById(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);
        return pagination;
    }
}
