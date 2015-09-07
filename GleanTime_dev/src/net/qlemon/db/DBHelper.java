package net.qlemon.db;

import java.util.ArrayList;
import java.util.List;

import net.qlemon.data.CalendarData;
import net.qlemon.data.CalendarGroup;
import net.qlemon.data.CalendarInfo;
import net.qlemon.data.Define;
import net.qlemon.data.GTCalendar;
import net.qlemon.utils.QLog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "testtest.db";
	private static final int DB_VERSION = 1;
	private static final String CINFO_TABLE = "calendarinfo";
	private static final String CDATA_TABLE = "calendardata";
	private static final String CGROUP_TABLE = "calendargroup";
	private static SQLiteDatabase mDB;
	private static DBHelper instance;
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	public static DBHelper getInstance(Context context) {
		// TODO Auto-generated constructor stub
		if(instance == null) {
			instance = new DBHelper(context.getApplicationContext());
			mDB = instance.getWritableDatabase();
		}
		return instance;
	}
	
	public List<GTCalendar> selectAllGTCalendars() {
		List<GTCalendar> gtCalendars = new ArrayList<GTCalendar>();
				
		String selectInfoSQL = "select " + Define.CI_QID + ", " + Define.CI_TYPE + "," + Define.CI_SUBTYPE + "," + Define.CI_CATEGORY + "," 
		+ Define.CI_TITLE + "," + Define.CI_DESCRIPTION + "," + Define.CI_DURATION + "," + Define.CI_PRICE + "," + Define.CI_STARTDATE + "," 
		+ Define.CI_ENDDATE + "," + Define.CI_STATE + "," + Define.CI_PUBLISHER + "," +	Define.CI_MODIFIER + "," + Define.CI_ICON + "," 
		+ Define.CI_EDITABLE + "," + Define.CI_COLOR + "," + Define.CI_VER + "," + Define.CI_KEYWORD + "," + Define.CI_OPENTYPE + "," 
		+ Define.CI_PUBLISHSTATE + "," + Define.CI_USEREDITED + "," + Define.CI_RECEIVENOTIFY + "," + Define.CI_COUNTRY + "," + Define.CI_LANGUAGE 
		+ "," + Define.CI_TIMEINFO + " from " + CINFO_TABLE;				

		Cursor cursor = mDB.rawQuery(selectInfoSQL, null);		
		cursor.moveToFirst();		
		while(!cursor.isAfterLast()) {
			//select one CalendarInfo
			List<CalendarData> cDataList = new ArrayList<CalendarData>();
			CalendarInfo info = new CalendarInfo(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), 
					cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getInt(8), 
					cursor.getInt(9), cursor.getInt(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), 
					cursor.getInt(14)>0, cursor.getInt(15), cursor.getString(16), cursor.getString(17), cursor.getInt(18), 
					cursor.getInt(19), cursor.getInt(20)>0, cursor.getInt(21)>0, cursor.getString(22), cursor.getString(23), cursor.getString(24));
			
			String selectDataSQL = "select " + Define.CD_ITEMID + "," + Define.CD_CALENDARID + "," + Define.CD_NAME + "," + Define.CD_ITEMTYPE + "," + Define.CD_STARTDATE + "," 
					+ Define.CD_ENDDATE + "," + Define.CD_STARTTIME + "," + Define.CD_ENDTIME + "," + Define.CD_DATETYPE + "," + Define.CD_NOTITYPE + "," + Define.CD_PRENOTY + "," 
					+ Define.CD_ITEMTEXT + "," + Define.CD_OPTION + "," + Define.CD_MEMO +"," + Define.CD_LINK + "," + Define.CD_LOCATION + "," + Define.CD_SPECIALDAYINFO + "," 
					+ Define.CD_ISALLDAY + "," +  Define.CD_PICTUREURL + " from " + CDATA_TABLE + " where " + Define.CD_CALENDARID + " = " + info.getQid();
			Cursor dCursor = mDB.rawQuery(selectDataSQL, null);
			
			//using CalendarID, get CalendarDataList
			dCursor.moveToFirst();
			while(!dCursor.isAfterLast()) {
				CalendarData itemData = new CalendarData(dCursor.getString(0), dCursor.getString(1), dCursor.getString(2), dCursor.getInt(3), 
						dCursor.getInt(4), dCursor.getInt(5), dCursor.getInt(6), dCursor.getInt(7), dCursor.getInt(8), dCursor.getInt(9), dCursor.getInt(10),
						dCursor.getString(11), dCursor.getString(12), dCursor.getString(13), dCursor.getString(14), dCursor.getString(15), dCursor.getString(16),
						dCursor.getInt(17)>0, dCursor.getString(18));
				cDataList.add(itemData);				
				dCursor.moveToNext();
			}
			//add To gtCalendar Data
			gtCalendars.add(new GTCalendar(info, cDataList));			
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return gtCalendars;
	}
	
	public List<CalendarGroup> selectAllCalendarGroups() {
		List<CalendarGroup> calGroup = new ArrayList<CalendarGroup>();

		String selectSql= "select " + Define.CG_ID + "," + Define.CG_NAME 
				+ "," + Define.CG_ICON + "," + Define.CG_ISAPPLY + "," 
				+ Define.CG_ISSHOW + "," + Define.CG_ISALARM + "," + Define.CG_CALTYPE + " from " + CGROUP_TABLE;
				
		Cursor cursor = mDB.rawQuery(selectSql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			CalendarGroup group = new CalendarGroup(cursor.getString(0), cursor.getString(1), cursor.getString(2), 
					cursor.getInt(3)>0, cursor.getInt(4)>0, cursor.getInt(5)>0, cursor.getInt(6));
			
			calGroup.add(group);
			
			cursor.moveToNext();
		}
		
		return calGroup;
	}
	
	public boolean  insertGTCalendar(GTCalendar gtCalendar) {
		// TODO Auto-generated method stub
		CalendarInfo info = gtCalendar.getcInfo();
		CalendarGroup group = new CalendarGroup(info.getQid(), info.getTitle(), info.getIcon(), false, false, false, Define._CALENDAR_TYPE_GT);		
		
		if(insertCalendarGroup(group) && insertCalendarInfo(gtCalendar.getcInfo()) && insertCalendarData(gtCalendar.getcData())) 
			return true;
		
		return false;		
	}
	
	/**
	 * JDI
	 * Load OS CalendarInfo then insert into CalendarGroup 
	 * **/
	public boolean insertOSCalendar() {
//		Define._CALENDAR_TYPE_OS;
		return false;
	}
	
	public boolean insertCalendarInfo(CalendarInfo info) {
		boolean result = false;
		
		ContentValues values = new ContentValues();
		values.put(Define.CI_QID, info.getQid());
		values.put(Define.CI_TYPE, info.getType());
		values.put(Define.CI_SUBTYPE, info.getSubType());
		values.put(Define.CI_CATEGORY, info.getCategory());
		values.put(Define.CI_TITLE, info.getTitle());
		values.put(Define.CI_DESCRIPTION, info.getDescription());
		values.put(Define.CI_DURATION, info.getDuration());
		values.put(Define.CI_PRICE, info.getPrice());
		values.put(Define.CI_STARTDATE, info.getStartDate());
		values.put(Define.CI_ENDDATE, info.getEndDate());
		values.put(Define.CI_STATE, info.getState());
		values.put(Define.CI_PUBLISHER, info.getPublisher());
		values.put(Define.CI_MODIFIER, info.getModifier());
		values.put(Define.CI_ICON, info.getIcon());
		values.put(Define.CI_EDITABLE, info.isEditable());
		values.put(Define.CI_COLOR, info.getColor());
		values.put(Define.CI_VER, info.getVer());
		values.put(Define.CI_KEYWORD, info.getKeyword());
		values.put(Define.CI_OPENTYPE, info.getOpentype());
		values.put(Define.CI_PUBLISHSTATE, info.getPublishState());
		values.put(Define.CI_USEREDITED, info.isUserEdited());
		values.put(Define.CI_RECEIVENOTIFY, info.isReceiveNotify());
		
		if(mDB.insert(CINFO_TABLE, null, values) != -1) result = true;
		
		return result;
	}
	
	public boolean deleteCalendarInfo(String calendarID) {
		boolean result = false; 
		
		if(mDB.delete(CINFO_TABLE, Define.CI_QID + "=" + calendarID, null) > 0) result = true; 
		
		return result;
	}
	
	public boolean updateCalendarInfo(CalendarInfo info) {
		boolean result = false;
		
		ContentValues values = new ContentValues();
		values.put(Define.CI_QID, info.getQid());
		values.put(Define.CI_TYPE, info.getType());
		values.put(Define.CI_SUBTYPE, info.getSubType());
		values.put(Define.CI_CATEGORY, info.getCategory());
		values.put(Define.CI_TITLE, info.getTitle());
		values.put(Define.CI_DESCRIPTION, info.getDescription());
		values.put(Define.CI_DURATION, info.getDuration());
		values.put(Define.CI_PRICE, info.getPrice());
		values.put(Define.CI_STARTDATE, info.getStartDate());
		values.put(Define.CI_ENDDATE, info.getEndDate());
		values.put(Define.CI_STATE, info.getState());
		values.put(Define.CI_PUBLISHER, info.getPublisher());
		values.put(Define.CI_MODIFIER, info.getModifier());
		values.put(Define.CI_ICON, info.getIcon());
		values.put(Define.CI_EDITABLE, info.isEditable());
		values.put(Define.CI_COLOR, info.getColor());
		values.put(Define.CI_VER, info.getVer());
		values.put(Define.CI_KEYWORD, info.getKeyword());
		values.put(Define.CI_OPENTYPE, info.getOpentype());
		values.put(Define.CI_PUBLISHSTATE, info.getPublishState());
		values.put(Define.CI_USEREDITED, info.isUserEdited());
		values.put(Define.CI_RECEIVENOTIFY, info.isReceiveNotify());
		
		if(mDB.update(CINFO_TABLE, values, Define.CI_QID + "=" + info.getQid(), null) > 0) result = true;
		
		return result;
	}
	
	public boolean insertCalendarData(List<CalendarData> cData) {
		boolean result = false;
		
		for(int i = 0; i < cData.size(); i++) {
			CalendarData data = cData.get(i);
			
			ContentValues values = new ContentValues();
			values.put(Define.CD_ITEMID, data.getItemID());
			values.put(Define.CD_CALENDARID, data.getCalendarID());
			values.put(Define.CD_NAME, data.getName());
			values.put(Define.CD_ITEMTYPE, data.getItemType());
			values.put(Define.CD_STARTDATE, data.getStartDate());
			values.put(Define.CD_ENDDATE, data.getEndDate());
			values.put(Define.CD_STARTTIME, data.getStartTime());
			values.put(Define.CD_ENDTIME, data.getEndTime());
			values.put(Define.CD_DATETYPE, data.getDateType());
			values.put(Define.CD_NOTITYPE, data.getNotiType());
			values.put(Define.CD_PRENOTY, data.getPreNoty());
			values.put(Define.CD_ITEMTEXT, data.getText());
			values.put(Define.CD_MEMO, data.getMemo());
			values.put(Define.CD_LINK, data.getLink());
			values.put(Define.CD_LOCATION, data.getLocationInfo());
			values.put(Define.CD_SPECIALDAYINFO, data.getSpecialDayInfo());
			values.put(Define.CD_ISALLDAY, data.isAllday());
			values.put(Define.CD_PICTUREURL, data.getPictureURL());
			if(mDB.insert(CDATA_TABLE, null, values) != -1) result = true;		
		}
		return result;
	}
	
	public boolean deleteCalendarDataByID(String itemID) {
		boolean result = false;
		
		if(mDB.delete(CDATA_TABLE, Define.CD_ITEMID + "=" + itemID, null) > 0) result = true;
		
		return result;
	}

	public boolean updateCalendarDataByID(CalendarData data) {
		boolean result = false;
		
		ContentValues values = new ContentValues();
		values.put(Define.CD_ITEMID, data.getItemID());
		values.put(Define.CD_CALENDARID, data.getCalendarID());
		values.put(Define.CD_NAME, data.getName());
		values.put(Define.CD_ITEMTYPE, data.getItemType());
		values.put(Define.CD_STARTDATE, data.getStartDate());
		values.put(Define.CD_ENDDATE, data.getEndDate());
		values.put(Define.CD_STARTTIME, data.getStartTime());
		values.put(Define.CD_ENDTIME, data.getEndTime());
		values.put(Define.CD_DATETYPE, data.getDateType());
		values.put(Define.CD_NOTITYPE, data.getNotiType());
		values.put(Define.CD_PRENOTY, data.getPreNoty());
		values.put(Define.CD_ITEMTEXT, data.getText());
		values.put(Define.CD_MEMO, data.getMemo());
		values.put(Define.CD_LINK, data.getLink());
		values.put(Define.CD_LOCATION, data.getLocationInfo());
		values.put(Define.CD_SPECIALDAYINFO, data.getSpecialDayInfo());
		values.put(Define.CD_ISALLDAY, data.isAllday());
		values.put(Define.CD_PICTUREURL, data.getPictureURL());
		
		if(mDB.update(CDATA_TABLE, values, Define.CD_ITEMID + "=" + data.getItemID(), null) > 0) result = true; 
		
		
		return result;
	}
	
	public boolean insertCalendarGroup(CalendarGroup group) {
		boolean result = false;
		
		ContentValues values = new ContentValues();
		values.put(Define.CG_ID, group.getCalendarID());
		values.put(Define.CG_NAME, group.getGroupName());
		values.put(Define.CG_ICON, group.getGroupIcon());
		values.put(Define.CG_ISAPPLY, group.isApply());
		values.put(Define.CG_ISSHOW, group.isShowing());
		values.put(Define.CG_ISALARM, group.isAlarm());
		values.put(Define.CG_CALTYPE, group.getCalType() + "");
		
		if(mDB.insert(CGROUP_TABLE, null, values) != -1) result = true;	
		
		return result;
	}
	
	/**
	 * delete by calendarInfo table, cascade
	//when group deleted, calendarInfo & calendarData delete too 
	public boolean deleteCalendarGroup(String calendarID) {
		boolean result = false;
		return result;
	}
	**/
	
	/**
	 * updateCalendarGroup(CalendarGroup group, String calendarID)
	 * group의 State Update 
	 * 
	 * **/
	// 
	public boolean updateCalendarGroup(CalendarGroup group, String calendarID) {
		boolean result = false;
		
		ContentValues values = new ContentValues();
		values.put(Define.CG_ID, group.getCalendarID());
		values.put(Define.CG_NAME, group.getGroupName());
		values.put(Define.CG_ICON, group.getGroupIcon());
		values.put(Define.CG_ISAPPLY, group.isApply());
		values.put(Define.CG_ISSHOW, group.isShowing());
		values.put(Define.CG_ISALARM, group.isAlarm());
		values.put(Define.CG_CALTYPE, group.getCalType() + "");
		if(mDB.update(CGROUP_TABLE, values, Define.CG_ID + "=" + calendarID, null) > 0) result = true;	
		
		return result;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		QLog.e("JDI", "db OnCreated");
		
		//for Using Foreign Key
		String sql = "PRAGMA foreign_keys = ON";
		db.execSQL(sql);
		
		sql = "create table " + CINFO_TABLE + "("
				+ Define.CI_QID + " text primary key,"
				+ Define.CI_TYPE + " integer,"
				+ Define.CI_SUBTYPE + " integer,"
				+ Define.CI_CATEGORY + " integer,"
				+ Define.CI_TITLE + " text,"
				+ Define.CI_DESCRIPTION + " text,"
				+ Define.CI_DURATION + " integer,"
				+ Define.CI_PRICE + " text,"
				+ Define.CI_STARTDATE + " integer,"
				+ Define.CI_ENDDATE + " integer,"
				+ Define.CI_STATE + " integer,"
				+ Define.CI_PUBLISHER + " text,"
				+ Define.CI_MODIFIER + " text,"
				+ Define.CI_ICON + " text,"
				+ Define.CI_EDITABLE + " bool,"
				+ Define.CI_COLOR + " integer,"
				+ Define.CI_VER + " text,"
				+ Define.CI_KEYWORD + " text,"
				+ Define.CI_OPENTYPE + " integer,"
				+ Define.CI_PUBLISHSTATE + " integer,"
				+ Define.CI_USEREDITED + " bool,"
				+ Define.CI_RECEIVENOTIFY + " bool,"
				+ Define.CI_COUNTRY + " text,"
				+ Define.CI_LANGUAGE + " text," 
				+ Define.CI_TIMEINFO + " text)";				
		
		db.execSQL(sql);
		
		sql = "create table " + CGROUP_TABLE + "("
				+ Define.CG_ID + " text," 
				+ Define.CG_NAME + " text," 
				+ Define.CG_ICON + " text," 
				+ Define.CG_ISAPPLY + " bool," 
				+ Define.CG_ISSHOW + " bool," 
				+ Define.CG_ISALARM + " bool,"
				+ Define.CG_CALTYPE + " integer,"
				+ "foreign key(" + Define.CG_NAME + ") references " + CGROUP_TABLE + "(" + Define.CI_TITLE + ") ON UPDATE CASCADE, "
				+ "foreign key(" + Define.CG_ID + ") references " + CGROUP_TABLE + "(" + Define.CI_QID + ") ON DELETE CASCADE)";
		
		db.execSQL(sql);
		
		sql = "create table " + CDATA_TABLE +"("
				+ Define.CD_ITEMID + " text, "
				+ Define.CD_CALENDARID + " text, "
				+ Define.CD_NAME + " text, "
				+ Define.CD_ITEMTYPE + " integer, "
				+ Define.CD_STARTDATE + " integer, "
				+ Define.CD_ENDDATE + " integer, "
				+ Define.CD_STARTTIME + " integer, "
				+ Define.CD_ENDTIME + " integer, "
				+ Define.CD_DATETYPE + " integer, "
				+ Define.CD_NOTITYPE + " integer, "
				+ Define.CD_PRENOTY + " integer, "
				+ Define.CD_ITEMTEXT + " text, "
				+ Define.CD_OPTION + " text,"				
				+ Define.CD_MEMO + " text, "
				+ Define.CD_LINK + " text, "
				+ Define.CD_LOCATION + " text, "
				+ Define.CD_SPECIALDAYINFO + " text, "
				+ Define.CD_ISALLDAY + " bool, "
				+ Define.CD_PICTUREURL + " text, "
				+ "foreign key(" + Define.CD_CALENDARID + ") references " + CINFO_TABLE + "(" + Define.CI_QID + ") ON DELETE CASCADE)";
		
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		QLog.e("JDI", "ON Upgrade");
		
	}

	
}
