package duomi.mongodb.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Component;

import duomi.mongodb.bean.Article;
import duomi.mongodb.dao.ArticleDao;
import duomi.mongodb.dao.MongodbBaseDao;
import org.springframework.stereotype.Component;

@Component
public class ArticleDaoImpl extends MongodbBaseDao<Article> implements ArticleDao {

	public void add(Article article) {
		super.save(article);
	}

	public void remove(Article article) {
		super.deleteByCondition(article);
	}

	public Article selectOne(Article article) {
		return (Article) super.findOne(article);
	}

	public List<Article> selectList(Article article) {
		return super.findByCondition(article);
	}

	public List<Article> selectAll() {
		return super.findAll();
	}

}
