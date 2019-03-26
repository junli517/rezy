package com.rezy.dialog.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rezy.dialog.model.entity.UserEntity;
import com.rezy.dialog.model.vo.base.ApiResult;
import com.rezy.dialog.service.user.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户id查询用户")
	public ApiResult getById(@PathVariable("id") Integer id) {
		UserEntity user = userService.selectById(id);
		return ApiResult.success(user);
	}
}
