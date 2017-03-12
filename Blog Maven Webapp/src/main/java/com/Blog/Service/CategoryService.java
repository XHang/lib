package com.Blog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Dao.ArticleDAO;
import com.Blog.Dao.CategoryDAO;
import com.Blog.Model.Category;
@Service
public class CategoryService {
	private CategoryDAO dao;
	ArticleDAO articleDAO;
	
	//根据用户id获取用户创建的所有类别对象
	public List<Category> getCategorys(int id) {
		return dao.selectCategoryById(id);
	}

	public CategoryDAO getDao() {
		return dao;
	}
	@Autowired
	public void setDao(CategoryDAO dao) {
		this.dao = dao;
	}
	//为用户添加一个类别，类别对象必须持有用户对象的id
	public void addCategory(Category category){
		dao.saveCategory(category);
	}
	//删除用户类别，并将类别下面所有的文章删除
	public void deleteCategory(Category category){
		articleDAO.deleteByCategoryID(category.getId());
		dao.delete(category);

	}
	public void modifiCategory(Category category){
		dao.updateCategory(category);
	}
	//获取用户创建的类别总数
	public int  getCategoryTotal(int userid){
		return dao.selectTotalByUserid(userid);
		
	}

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}
	@Autowired
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	

}
