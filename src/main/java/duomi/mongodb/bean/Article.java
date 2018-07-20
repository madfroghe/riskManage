package duomi.mongodb.bean;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import duomi.mongodb.dao.annotation.QueryField;
import duomi.mongodb.dao.annotation.QueryType;

@Document(collection = "article")
public class Article {
	@Id
	@QueryField
	private String id;

	@QueryField(type = QueryType.EQUALS, attribute = "appno")
	private String appno;

	@QueryField
	private String title;

	@QueryField(type = QueryType.LIKE, attribute = "content")
	private String content;

	@QueryField(type = QueryType.IN, attribute = "title")
	private List<String> queryTitles;

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getQueryTitles() {
		return queryTitles;
	}

	public void setQueryTitles(List<String> queryTitles) {
		this.queryTitles = queryTitles;
	}

}
