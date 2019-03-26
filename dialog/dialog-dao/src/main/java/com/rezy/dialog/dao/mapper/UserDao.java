package com.rezy.dialog.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.rezy.dialog.model.entity.UserEntity;

@Mapper
public interface UserDao {

	int deleteByPrimaryKey(Integer id);

	int insert(UserEntity record);

	int insertSelective(UserEntity record);

	UserEntity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserEntity record);

	int updateByPrimaryKey(UserEntity record);
}