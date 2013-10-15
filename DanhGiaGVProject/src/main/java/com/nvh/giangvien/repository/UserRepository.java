package com.nvh.giangvien.repository;

import org.springframework.data.repository.CrudRepository;

import com.nvh.giangvien.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	//nhung phuong thuc dac bien trong day
	
}
