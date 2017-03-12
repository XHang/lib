package com.Blog.Dao;

import java.util.List;

import com.Blog.Model.Article;

public interface ArticleDAO {

	List<Article> selectNewArticle(int id);

	List<Article> selectHotArticle(int id);

	void saveArticle(Article article);
	public Article selectArticle(int id);

	void deleteByCategoryID(int categoryid);

	int selectArticleTatal(int userid);

	void deleteArticleById(int id);
	
	List<Article> selectArticleByCategoryId(int categoryId);

	List<Article> search(String keyWord);

	List<Article> getAllArticle(int userid);

	void update(Article article);

}
