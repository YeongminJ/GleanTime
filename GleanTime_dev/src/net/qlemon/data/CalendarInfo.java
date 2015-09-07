package net.qlemon.data;


public class CalendarInfo {

	private String qid;						//Calendar ID
	private int type;		
	private int subType;
	private int category;					//Calendar Category

	private String title;					//Calendar Title
	private String description;					//Calendar Comment
	private int duration;					
	private String price;

	private int startDate;
	private int endDate;
	private int state;
	private String publisher;
	private String modifier;
	private String icon;
	private boolean editable;
	private int color;
	private String ver;
	private String keyword;
	private int opentype;

	private int publishState;
	private boolean userEdited;
	private boolean receiveNotify;
	
	private String country;
	private String language;
	private String timeInfo;
	
	public CalendarInfo() {
		
	}
	
	public CalendarInfo(String qid, int type, int subtype, int category, String title, String description, int duration, String price, 
			int startDate, int endDate, int state, String publisher, String modifier, String icon, boolean editable, int color, String ver, 
			String keyword, int opentype, int publishState, boolean userEdited, boolean receiveNotify, String country, String language, String timeinfo) {
		this.qid = qid;
		this.type = type;
		this.subType = subtype;
		this.category = category;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
		this.publisher = publisher;
		this.modifier = modifier;
		this.icon = icon;
		this.editable = editable;
		this.color = color;
		this.ver = ver;
		this.keyword = keyword;
		this.opentype = opentype;
		this.publishState = publishState;
		this.userEdited = userEdited;
		this.receiveNotify = receiveNotify;
		this.country = country;
		this.language = language;
		this.timeInfo = timeinfo;
	}
	
	

	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSubType() {
		return subType;
	}
	public void setSubType(int i) {		
		this.subType = i;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getStartDate() {
		return startDate;
	}
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getOpentype() {
		return opentype;
	}
	public void setOpentype(int opentype) {
		this.opentype = opentype;
	}
	public int getPublishState() {
		return publishState;
	}
	public void setPublishState(int publishState) {
		this.publishState = publishState;
	}
	public boolean isUserEdited() {
		return userEdited;
	}
	public void setUserEdited(boolean userEdited) {
		this.userEdited = userEdited;
	}
	public boolean isReceiveNotify() {
		return receiveNotify;
	}
	public void setReceiveNotify(boolean receiveNotify) {
		this.receiveNotify = receiveNotify;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTimeInfo() {
		return timeInfo;
	}

	public void setTimeInfo(String timeInfo) {
		this.timeInfo = timeInfo;
	}
	
}
