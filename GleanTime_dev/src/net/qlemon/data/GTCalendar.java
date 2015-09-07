package net.qlemon.data;

import java.util.List;

public class GTCalendar {
	private CalendarInfo cInfo;
	private List<CalendarData> cData;
	
	public CalendarInfo getcInfo() {
		return cInfo;
	}
	public void setcInfo(CalendarInfo cInfo) {
		this.cInfo = cInfo;
	}
	public List<CalendarData> getcData() {
		return cData;
	}
	public void setcData(List<CalendarData> cData) {
		this.cData = cData;
	}
	
	public GTCalendar(CalendarInfo info, List<CalendarData> data)
	{
		cInfo = info;
		cData = data;
	}
}
