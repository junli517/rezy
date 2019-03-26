package com.rezy.dialog.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rezy.dialog.dao.mapper.UserDao;
import com.rezy.dialog.model.entity.UserEntity;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert(UserEntity entity) {
		return userDao.insert(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer deleteById(Integer id) {
		return userDao.deleteByPrimaryKey(id);
	}

	@Override
	public UserEntity selectById(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}
}
