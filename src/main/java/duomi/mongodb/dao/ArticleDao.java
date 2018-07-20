package duomi.mongodb.dao;

import java.util.List;

import duomi.mongodb.bean.Article;

public interface ArticleDao {
	public void add(Article article);

	public void remove(Article article);

	public Article selectOne(Article article);

	public List<Article> selectList(Article article);

	public List<Article> selectAll();
}
