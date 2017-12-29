package com.switch007.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.switch007.model.FreeAgent;

@Mapper
public interface FreeAgentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FreeAgent record);

    int insertSelective(FreeAgent record);

    FreeAgent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreeAgent record);

    int updateByPrimaryKey(FreeAgent record);

	int batchSave(List<FreeAgent> list);
}