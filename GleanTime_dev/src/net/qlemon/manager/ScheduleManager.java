package net.qlemon.manager;

import java.util.ArrayList;
import java.util.List;

import net.qlemon.data.CalendarData;
import net.qlemon.data.CalendarGroup;
import net.qlemon.data.GTCalendar;
import net.qlemon.db.DBHelper;
import android.content.Context;

public class ScheduleManager {

	public DBHelper dbHelper;
	
	public List<GTCalendar> shareCalendars;
	public List<CalendarGroup> calGroups;
	
	public ScheduleManager(Context context)
	{
		dbHelper = DBHelper.getInstance(context);
		shareCalendars = new ArrayList<GTCalendar>();
	}
	
	public boolean loadSchedule() {
		shareCalendars = dbHelper.selectAllGTCalendars();
		calGroups = dbHelper.selectAllCalendarGroups();
		
		return false;
	}
	
	public void addSchedule(GTCalendar gtCalendar) {
		String calendarID = gtCalendar.getcInfo().getQid();
		
		List<CalendarData> cData = gtCalendar.getcData();
		//set calendarData's ID using CalendarID
		for(int i = 0; i < cData.size(); i++) {
			cData.get(i).setItemID(calendarID + "_" + i);
		}
		
		//set itemID , then insert to Database
		boolean result = dbHelper.insertGTCalendar(gtCalendar);
		if(result) {
			if(shareCalendars != null)
				shareCalendars.add(gtCalendar);
		}
	}

	
	
}
