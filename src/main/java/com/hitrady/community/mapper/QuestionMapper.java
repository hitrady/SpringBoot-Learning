package com.hitrady.community.mapper;

import com.hitrady.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from question limit #{page},#{size}")
    public List<Question> list(@Param("page") Integer page,@Param("size") Integer size);

    @Select("select count(1) from question")
    public Integer count();

    @Select("select count(1) from question where creator=#{id}")
    public Integer countByUserId(@Param("id") Integer id);

    @Select("select * from question where creator=#{id} limit #{page},#{size}")
    public List<Question> listByUserId(@Param("id") Integer id, @Param("page") Integer page, @Param("size") Integer size);
}