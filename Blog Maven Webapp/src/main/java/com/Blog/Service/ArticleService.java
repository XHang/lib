package com.Blog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Dao.ArticleDAO;
import com.Blog.Model.Article;
@Service
public class ArticleService {
	private ArticleDAO dao;
	public List<Article> getNewArticle(int id) {
		return dao.selectNewArticle(id);
	}

	public List<Article> getHotArticle(int id) {
		return dao.selectHotArticle(id);
	}
	//保存用户的文文章到数据库
	public void save(Article article) {
		dao.saveArticle(article);
	}
	//获取用户下面有多少个文章
	public int getArticleTotal(int userid){
		return dao.selectArticleTatal(userid);
	}
	public Article getArticle(int id){
		return dao.selectArticle(id);
	}
	public void deleteArticleById(int  articleid){
		dao.deleteArticleById(articleid);
		
	}
	
	public ArticleDAO getDao() {
		return dao;
	}
	@Autowired
	public void setDao(ArticleDAO dao) {
		this.dao = dao;
	}
	//根据类别列表取出文章列表
	public List<Article> getArticleListByCategory(int categoryId) {
		return dao.selectArticleByCategoryId(categoryId);
	}

	public List<Article> search(String keyWord) {
		return dao.search("%"+keyWord+"%");
			
	}
	//获取用户所有的文章列表
	public List<Article> getAllArticle(int id) {
		return dao.getAllArticle(id);
		
	}

	public void update(Article article) {
		dao.update(article);
		
	}
	


}
