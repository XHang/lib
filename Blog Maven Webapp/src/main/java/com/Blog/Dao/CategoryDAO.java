package com.Blog.Dao;

import java.util.List;

import com.Blog.Model.Category;

public interface CategoryDAO {

	List<Category> selectCategoryById(int id);
	void createDefaultCategory(int userid);
	void saveCategory(Category category);
	void delete(Category category);
	int selectTotalByUserid(int userid);
	void updateCategory(Category category);

}
