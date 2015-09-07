package net.qlemon.data;

public class CalendarData {
	
//	Int subType	
//	int repeatCnt;			
//	String iconURL;
//	private String applyDate;
//	private String realDate;
	
	private String itemID;			//item ID			*******************        어쩌지             ****************************
	private String calendarID;		//Calendar ID
	private String name;			//Name
	private int itemType;			//CAL_ITEM_TYPE
	private int startDate;			//using at fixed date type /////////Start Apply Date
	private int endDate;			//using at fixed date type//////////End Apply Date
	private int startTime;
	private int endTime;
	private int dateType;			// LUNAR
	private int notiType;			//_CAL_ITEM_NOTI
	private int preNoty;			//_CAL_ITEM_PRENOTI
	private String text;			//ITEM Title
	private String option;			//Item Option
	private String memo;			//ITEM Memo
	private String link;			//ITEM Link
	private String locationInfo;	//Item Location
	private String specialDayInfo;	//Item SpecialInfo
	private boolean isAllday;		//Item isAllDay
	private String pictureURL;		//item PictureURL
	
	public CalendarData() {}
	
	public CalendarData(String itemID, String calendarID, String name,
			int itemType, int startDate, int endDate, int startTime,
			int endTime, int dateType, int notiType, int preNoty, String text,
			String option, String memo, String link, String locationInfo,
			String specialDayInfo, boolean isAllday, String pictureURL) {
		this.itemID = itemID;
		this.calendarID = calendarID;
		this.name = name;
		this.itemType = itemType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dateType = dateType;
		this.notiType = notiType;
		this.preNoty = preNoty;
		this.text = text;
		this.option = option;
		this.memo = memo;
		this.link = link;
		this.locationInfo = locationInfo;
		this.specialDayInfo = specialDayInfo;
		this.isAllday = isAllday;
		this.pictureURL = pictureURL;
	}
	
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getCalendarID() {
		return calendarID;
	}
	public void setCalendarID(String calendarID) {
		this.calendarID = calendarID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
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
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getDateType() {
		return dateType;
	}
	public void setDateType(int dateType) {
		this.dateType = dateType;
	}
	public int getNotiType() {
		return notiType;
	}
	public void setNotiType(int notiType) {
		this.notiType = notiType;
	}
	public int getPreNoty() {
		return preNoty;
	}
	public void setPreNoty(int preNoty) {
		this.preNoty = preNoty;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLocationInfo() {
		return locationInfo;
	}
	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}
	public String getSpecialDayInfo() {
		return specialDayInfo;
	}
	public void setSpecialDayInfo(String specialDayInfo) {
		this.specialDayInfo = specialDayInfo;
	}
	public boolean isAllday() {
		return isAllday;
	}
	public void setAllday(boolean isAllday) {
		this.isAllday = isAllday;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
}
