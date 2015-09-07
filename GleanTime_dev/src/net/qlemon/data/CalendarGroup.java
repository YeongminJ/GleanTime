package net.qlemon.data;


public class CalendarGroup {
	private String calendarID;
	private String groupName;
	private String groupIcon;
	private boolean isApply;
	private boolean isShowing;
	private boolean isAlarm;
	private int calType;
		
	public CalendarGroup(String calendarID, String groupName, String groupIcon,
			boolean isApply, boolean isShowing, boolean isAlarm,
			int calType) {
		super();
		this.calendarID = calendarID;
		this.groupName = groupName;
		this.groupIcon = groupIcon;
		this.isApply = isApply;
		this.isShowing = isShowing;
		this.isAlarm = isAlarm;
		this.calType = calType;
	}
	
	public String getCalendarID() {
		return calendarID;
	}
	public void setCalendarID(String calendarID) {
		this.calendarID = calendarID;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupIcon() {
		return groupIcon;
	}
	public void setGroupIcon(String groupIcon) {
		this.groupIcon = groupIcon;
	}
	public boolean isApply() {
		return isApply;
	}
	public void setApply(boolean isApply) {
		this.isApply = isApply;
	}
	public boolean isShowing() {
		return isShowing;
	}
	public void setShowing(boolean isShowing) {
		this.isShowing = isShowing;
	}
	public boolean isAlarm() {
		return isAlarm;
	}
	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}
	public int getCalType() {
		return calType;
	}
	public void setCalType(int calType) {
		this.calType = calType;
	}
	
}
