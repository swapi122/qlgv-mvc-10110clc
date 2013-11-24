package com.nvh.giangvien.controller;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.nvh.applicationscope.UserGrid;
import com.nvh.giangvien.model.SearchCriteria;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private UserService userService;
	
	final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String logined() {
		return "manager";
	}
	
	// load UI quan ly user
		@RequestMapping(value = "qlgvgrid", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody
		UserGrid listGrid(
				@RequestParam(value = "page", required = false) Integer page,
				@RequestParam(value = "rows", required = false) Integer rows,
				@RequestParam(value = "sidx", required = false) String sortBy,
				@RequestParam(value = "sord", required = false) String order,
				@RequestParam(value = "_search", required = false) boolean isSearch,
				@RequestParam(value = "id", required = false) String id,
				@RequestParam(value = "hoten", required = false) String hoten,
				@RequestParam(value = "typeaccount", required = false) Integer typeaccount)
				throws UnsupportedEncodingException {
			logger.info("Listing contacts for grid with page: {}, rows: {}", page,
					rows);
			logger.info("Listing contacts for grid with sort: {}, order: {}",
					sortBy, order);
			logger.info("is search {}", isSearch);
			logger.info("Search field id : {}", id);
			logger.info("search field hoten : {}", hoten);
			logger.info("search field typeacount : {}", typeaccount);

			// id
			if (id == null) {
				id = "%";
			} else {
				id = "%" + id + "%";
			}

			// hoten
			if (hoten == null) {
				hoten = "%";
			} else {
				hoten = "%" + hoten + "%";
			}if(typeaccount == null){
				typeaccount = 1;
			}
			SearchCriteria criteria = new SearchCriteria();
			criteria.setId(id);
			criteria.setHoten(hoten);
			criteria.setTypeacount(typeaccount);

			// Process order by
			Sort sort = null;
			String orderBy = sortBy;
			if (orderBy != null && orderBy.equals("typeaccount"))
				orderBy = "typeaccount";
			if (orderBy != null && order != null) {
				if (order.equals("desc")) {
					sort = new Sort(Sort.Direction.DESC, orderBy);
				} else
					sort = new Sort(Sort.Direction.ASC, orderBy);
			}
			// Constructs page request for current page
			// Note: page number for Spring Data JPA starts with 0, while jqGrid
			// starts with 1

			PageRequest pageRequest = null;
			if (sort != null) {
				pageRequest = new PageRequest(page - 1, rows, sort);
			} else {
				pageRequest = new PageRequest(page - 1, rows);
			}

			/*
			 * Page<User> userPage = null; userPage =
			 * userService.findAllByPage(pageRequest);
			 */

			Page<User> userPage = userService.findUserByCriteria(criteria,
					pageRequest);

			// Construct the grid data that will return as JSON data
			UserGrid userGrid = new UserGrid();
			userGrid.setCurrentPage(userPage.getNumber() + 1);
			userGrid.setTotalPages(userPage.getTotalPages());
			userGrid.setTotalRecords(userPage.getTotalElements());
			userGrid.setUserData(Lists.newArrayList(userPage.iterator()));
			return userGrid;
		}
}
