package com.nvh.giangvien.service;

import java.util.List;

import com.nvh.giangvien.model.User;

public interface UserService {

	public List<User> findAll();

	public User findById(String id);

	public User save(User contact);

	public void delete(User contact);

}
