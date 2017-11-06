package com.switch007.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.switch007.model.Adminer;

@Mapper
public interface AdminerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Adminer record);

    int insertSelective(Adminer record);

    Adminer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Adminer record);

    int updateByPrimaryKey(Adminer record);
    
    Adminer selectByFileds(Map<String, Object> mp);
}