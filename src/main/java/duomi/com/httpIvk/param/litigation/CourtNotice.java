package duomi.com.httpIvk.param.litigation;

//法院公告
public class CourtNotice {
	private String announcementType; 		// 公告类型 
	private String content;         		// 公告内容 
	private String recordTime;          // 发布时间 
	private String name;        			  // 当事人   
	private String court;         			// 法院名称 
	
	public String getAnnouncementType() {
		return announcementType;
	}
	public void setAnnouncementType(String announcementType) {
		this.announcementType = announcementType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}

}
