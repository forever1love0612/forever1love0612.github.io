package com.pdsu.edu.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pdsu.edu.domain.User;
import com.pdsu.edu.service.UserService;

/**
 * 类说明：
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2012-3-25 下午02:32:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class UserServiceImplTest {
	@Autowired
	private UserService userService;

	@Test
	public void testAddUser() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("1234");
		userService.addUser(user);
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setId(1);
		user.setUsername("admin");
		user.setPassword("1234456");
		userService.updateUser(user);
	}

	@Test
	public void testDeleteUser() {
		userService.deleteUser(1);
	}

	@Test
	public void testFindUserById() {
		User user = userService.findUserById(2);
		System.out.println(user);
	}

	@Test
	public void testFindAllUser() {
		List<User> users = userService.findAllUser();
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Test
	public void testUserLogin() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("1234");
		System.out.println(userService.login(user));
	}
}
