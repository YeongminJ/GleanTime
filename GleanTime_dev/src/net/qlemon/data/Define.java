package net.qlemon.data;

public class Define {
	
	//Login & SignUp
	public static String LOGIN_PREF = "login_pref";
	public static String LOGIN_ID = "id";
	public static String LOGIN_PWD = "password";
	public static String SESSION_ID = "session_id";
	public static String SIGNUP_EMAIL = "email";
	public static String SIGNUP_PWD = "password";
	public static String SIGNUP_NICKNAME = "nickname";
	public static String SIGNUP_TYPE = "type";
	public static String OS = "os";
		
	//static enum _TYPE_CALL_ASP{TCA_LOGIN, TCA_REG_SID, TCA_CHK_VER, TCA_END};

	public static enum _USER_LEVEL {
		NORMAL_USER,
		MANAGER_USER,
		ADMIN_USER
	} 



	public static enum _MYSCAL_STATE{
	    MYSCAL_STATE_NONE,
	    MYSCAL_STATE_DELETED,
	    MYSCAL_STATE_NOTUSING,
	    MYSCAL_STATE_USING
	};

	/*
	public static enum _MYSCALITEM_TYPE{
	    MYSCALITEM_TYPE_NONE = 0,
	    MYSCALITEM_TYPE_MEMO = 1,
	    MYSCALITEM_TYPE_PIC = 2,
	    MYSCALITEM_TYPE_SCHEDULE = 3,
	    MYSCALITEM_TYPE_TODO = 4,
	    MYSCALITEM_TYPE_SHAREDCAL = 5
	} MYSCALITEM_TYPE;
	*/

	public static enum _APP_TYPE {
		APP_TYPE_FCAL_IOS,
	    APP_TYPE_FCAL_ANDROID,
	 	APP_TYPE_FCAL_PC,
	    APP_TYPE_NONE
	};



	public static enum _FILE_UP_TYPE {
		FILE_UP_TYPE_SHARECAL_ICON,
	    FILE_UP_TYPE_MYSHARECALLIST,
	 	FILE_UP_TYPE_MYPIC,
	    FILE_UP_TYPE_MYINFO
	};


	public static enum _CAL_ITEM_TYPE {
		CAL_ITEM_TYPE_SCHEDULE,
	    CAL_ITEM_TYPE_SPECIALDAY,
	 	CAL_ITEM_TYPE_TODO,
	    CAL_ITEM_TYPE_MEMO,
	 	CAL_ITEM_TYPE_MEMOPIC,
	    CAL_ITEM_TYPE_SHARECAL,
	    CAL_ITEM_TYPE_NONE
	};


	public static enum _CAL_ITEM_DATE_CAL_TYPE {
	    CAL_ITEM_DATE_CAL_GREGORIAN,
	    CAL_ITEM_TYPE_KOREAN_LUNAR,
	    CAL_ITEM_TYPE_JAPAN_LUNAR,
	    CAL_ITEM_TYPE_CHINA_LUNAR
	};


	public static enum _CAL_ITEM_SPECIAL_REPEAT_TYPE {
	    CAL_ITEM_SPECIAL_REPEAT_NONE,
	    CAL_ITEM_SPECIAL_REPEAT_YEAR	    
	};


	public static enum _CAL_ITEM_NOTI {
	    CAL_ITEM_NO_ALARM,
	    CAL_ITEM_REPEAT_NONE,
		CAL_ITEM_REPEAT_DAY,
	    CAL_ITEM_REPEAT_WEEK,
	    CAL_ITEM_REPEAT_MONTH,
	    CAL_ITEM_REPEAT_YEAR,
	};


	public static enum _CAL_ITEM_PRENOTI {
		CAL_ITEM_PRENOTI_NONE,
	    CAL_ITEM_PRENOTI_5MIN,
		CAL_ITEM_PRENOTI_1HOUR,
	    CAL_ITEM_PRENOTI_1DAY
	};

    public static enum _SHARECAL_ITEM_TYPE {
		SHARECAL_ITEM_TYPE_COMPANY,
	    SHARECAL_ITEM_TYPE_PRIVATE,
	 	SHARECAL_ITEM_TYPE_UPDATE,
	    SHARECAL_ITEM_TYPE_NONE
	};

	public static enum _SHARECAL_SUB_TYPE {
	    SHARECAL_SUB_TYPE_WEEKLY,
	    SHARECAL_SUB_TYPE_MONTHLY,
	    SHARECAL_SUB_TYPE_YEARLY,
	    SHARECAL_SUB_TYPE_FIXED,
	    SHARECAL_SUB_TYPE_FROMDATE,
	    SHARECAL_SUB_TYPE_DAILY
	};


	public static enum _SHARECAL_CATEGORY_TYPE {
	    SHARECAL_CATEGORY_THEME,
	    SHARECAL_CATEGORY_LEARNING,
	    SHARECAL_CATEGORY_SPORTS,
	    SHARECAL_CATEGORY_HEALTH,
	    SHARECAL_CATEGORY_TV,
	    SHARECAL_CATEGORY_TRAVEL,
	    SHARECAL_CATEGORY_CONCERT,
	    SHARECAL_CATEGORY_BRAND,
	    SHARECAL_CATEGORY_LBS,
	    SHARECAL_CATEGORY_OTHERS
	};

	public static enum _SHARECAL_PUBLISH_STATE {
	    SHARECAL_PUBLISH_STATE_NONE,
	    SHARECAL_PUBLISH_NOT_REGISTERD,
	    SHARECAL_PUBLISH_REGISTERED_NEED_UPDATE,
	    SHARECAL_PUBLISH_REGISTERED_UPDATED
	};


	public static enum _COMMON_NOTI_TYPE {
		COMMON_NOTI_TYPE_NORMAL,
	    COMMON_NOTI_TYPE_UPDATE,
	 	COMMON_NOTI_TYPE_IMPORTANT,
	 	COMMON_NOTI_TYPE_SERVERCHECK
	};


	public static enum _FRIEND_STATUS {
	    FRIEND_STATUS_NOT_FRIEND,
		FRIEND_STATUS_MAYBE,
	    FRIEND_STATUS_INVITE_SENT,
	 	FRIEND_STATUS_INVITE_RECEIVED,
	 	FRIEND_STATUS_FRIEND_OK,
	    FRIEND_STATUS_INVITE_REJECTED,
	    FRIEND_STATUS_BLOCKED_FRIEND
	};


	public static enum _FRIEND_CHANGE_CMD {
	    FRIEND_CHANGE_NONE,
	    FRIEND_CHANGE_INVITE_FRIEND,
	    FRIEND_CHANGE_ACCEPT_INVITE,
	    FRIEND_CHANGE_REJECT_INVITE,
	    FRIEND_CHANGE_BLOCK_FRIEND,
	    FRIEND_CHANGE_RELIVE_FRIEND
	};

/*
	public static enum _MYCAL_STATUS {
		MYCAL_STATUS_NOTUSING,
	    MYCAL_STATUS_USING,
	    MYCAL_STATUS_DELETED
	};
*/
	
	public static enum _USER_TYPE{
	    USER_TYPE_NO_NONE,
	    USER_TYPE_LOGIN_USER_QLEMON,
	    USER_TYPE_LOGIN_USER_FACEBOOK
	    //USER_TYPE_MEMBERSHIP_USER = 3,
	    //USER_TYPE_ADMIN = 4,
	};

	/*
	 public static enum _EXPAND_TABLE_TYPE{
	    EXPAND_TABLE_TYPE_MYSCAL = 0,
	    EXPAND_TABLE_TYPE_EDITSCAL = 1,
	    
	} EXPAND_TABLE_TYPE;
	*/
		
	public static enum _SPECIALDAY_OPTION_TYPE{
	    SPECIALDAY_OPTION_NONE,
	    SPECIALDAY_OPTION_HOLIDAY,
	    SPECIALDAY_OPTION_LUNARCAL,
	    SPECIALDAY_OPTION_HOLIDAY_LUNARCAL
	};


	public static enum _TODO_ITEM_STATE {
	    TODO_ITEM_STATE_NEW,
	    TODO_ITEM_STATE_FINISHED,
	    TODO_ITEM_STATE_DELETED
	};

	/*
	public static enum _FRIEND_STATE {
	    FRIEND_STATE_NONE      = 0,
	    FRIEND_STATE_INVITING	= 1,
	    FRIEND_STATE_REQUESTED	= 2,
	    FRIEND_STATE_FRIEND     = 3,
	    FRIEND_STATE_REJECTED   = 4,
	    FRIEND_STATE_REJECT_REVIVED   = 5,
	    
	}FRIEND_STATE;
	*/
	/*
	public static enum _SHARECAL_ORG_BY_TYPE {
	    SHARECAL_ORG_BY_SERVER     = 0,
	    SHARECAL_ORG_BY_ME	= 1,
	    SHARECAL_ORG_BY_USERCONVERTER     = 2,
	}SHARECAL_ORG_BY_TYPE;
	*/
	public static enum _CALENDAR_TYPE {
		OS_CALENDAR,
		GT_CALENDAR
	}
	
	public static int _CALENDAR_TYPE_GT = 1;
	public static int _CALENDAR_TYPE_OS = 1;
	
	//calendar XML TAG CONSTANT
	public static String CAL_QLSHARECALENDAR = "QLShareCalendar";
	public static String CAL_VERSION = "Ver";
	public static String CAL_TYPE = "Type";
	public static String CAL_SUBTYPE = "SubType";
	public static String CAL_CATEGORY = "Category";
	public static String CAL_TITLE = "Title";
	public static String CAL_DESCRIPTION = "Description";
	public static String CAL_PRICE = "Price";
	public static String CAL_START_DATE = "StartDate";
	public static String CAL_END_DATE = "EndDate";
	public static String CAL_PUBLISHER = "Publisher";
	public static String CAL_MODIFIER = "Modifier";
	public static String CAL_EDITABLE = "Editable";
	public static String CAL_OPENTYPE = "OpenType";
	public static String CAL_ICON = "Icon";
	public static String CAL_QID = "QID";
	public static String CAL_COUNTRY = "Country";
	public static String CAL_LANGUAGE = "Language";
	public static String CAL_TIMEINFO = "TimeInfo";
	
	
	//calendar Item XML TAG CONSTANT
	public static String CAL_ITEM_CALDATA = "CalData";
	public static String CAL_ITEM_DAYDATA = "DayData";
	public static String CAL_ITEM_NAME = "Name";
	public static String CAL_ITEM_SHARECALDATA = "ShareCalData";
	public static String CAL_ITEM_TYPE = "Type";
	public static String CAL_ITEM_START_DATE = "StartDate";
	public static String CAL_ITEM_END_DATE = "EndDate";
	public static String CAL_ITEM_START_TIME = "StartTime";
	public static String CAL_ITEM_END_TIME = "EndTime";
	public static String CAL_ITEM_DATECALTYPE = "DateCalType";
	public static String CAL_ITEM_NOTITYPE = "NotiType";
	public static String CAL_ITEM_PRENOTI = "PreNoti";
	public static String CAL_ITEM_TEXT = "Text";
	public static String CAL_ITEM_OPTION = "Option";
	public static String CAL_ITEM_MEMO = "Memo";
	public static String CAL_ITEM_LINK = "Link";
	public static String CAL_ITEM_LOCATION = "Location";
	public static String CAL_ITEM_SPECIALINFO = "SpecialDayInfo";	
	
	//calendarInfo Database column 
	public static String CI_QID = "qid";			
	public static String CI_TYPE = "type";		
	public static String CI_SUBTYPE = "subtype";
	public static String CI_CATEGORY = "category";
	public static String CI_TITLE = "title";					
	public static String CI_DESCRIPTION = "description";
	public static String CI_DURATION = "duration";					
	public static String CI_PRICE = "price";
	public static String CI_STARTDATE = "startdate";
	public static String CI_ENDDATE = "enddate";
	public static String CI_STATE = "state";
	public static String CI_PUBLISHER = "publisher";
	public static String CI_MODIFIER = "modifier";
	public static String CI_ICON = "icon";
	public static String CI_EDITABLE ="editable";
	public static String CI_COLOR = "color";
	public static String CI_VER = "ver";
	public static String CI_KEYWORD = "keyword";
	public static String CI_OPENTYPE = "opentype";
	public static String CI_PUBLISHSTATE = "publishstate";
	public static String CI_USEREDITED = "useredited";
	public static String CI_RECEIVENOTIFY = "receivenotify";
	public static String CI_COUNTRY = "country";
	public static String CI_LANGUAGE = "language";
	public static String CI_TIMEINFO = "timeinfo";
	
	
	//calendarData Database column
	public static String CD_ITEMID = "itemid";			
	public static String CD_CALENDARID = "calendarid";			
	public static String CD_NAME = "name";			
	public static String CD_ITEMTYPE = "itemtype";			
	public static String CD_STARTDATE = "startdate";			
	public static String CD_ENDDATE = "enddate";
	public static String CD_STARTTIME = "starttime";		
	public static String CD_ENDTIME = "endtime";		
	public static String CD_DATETYPE = "datetype";			
	public static String CD_NOTITYPE = "notitype";			
	public static String CD_PRENOTY = "prenoty";			
	public static String CD_ITEMTEXT = "itemtext";		
	public static String CD_OPTION = "option";
	public static String CD_MEMO = "memo";			
	public static String CD_LINK = "link";			
	public static String CD_LOCATION= "location";
	public static String CD_SPECIALDAYINFO = "specialdayinfo";
	public static String CD_ISALLDAY = "isallday";
	public static String CD_PICTUREURL = "pictureurl";
	
	//calendarGroup Database column
	public static String CG_ID = "id";
	public static String CG_NAME= "groupname";
	public static String CG_ICON= "groupicon";
	public static String CG_ISAPPLY= "apply";
	public static String CG_ISSHOW= "show";
	public static String CG_ISALARM= "alarm";
	public static String CG_CALTYPE= "calendartype";	//os or gt
}
