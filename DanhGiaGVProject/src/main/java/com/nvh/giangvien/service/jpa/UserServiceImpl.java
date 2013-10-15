package com.nvh.giangvien.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.repository.UserRepository;
import com.nvh.giangvien.service.UserService;

@Service("JpaUserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(userRepository.findAll());
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void delete(User contact) {
		// TODO Auto-generated method stub
		userRepository.delete(contact);
	}

}
