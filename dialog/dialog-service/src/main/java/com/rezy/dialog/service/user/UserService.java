package com.rezy.dialog.service.user;

import com.rezy.dialog.model.entity.UserEntity;

/**
 * @ClassName: UserService
 * @Description: (描述)
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月23
 */
public interface UserService {
	Integer insert(UserEntity entity);

	Integer deleteById(Integer id);

	UserEntity selectById(Integer id);
}
