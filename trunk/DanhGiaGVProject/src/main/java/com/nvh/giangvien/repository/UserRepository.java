package com.nvh.giangvien.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nvh.giangvien.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

	//nhung phuong thuc dac bien trong day
	
}
