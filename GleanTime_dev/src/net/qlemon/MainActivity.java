package net.qlemon;

import java.util.ArrayList;
import java.util.List;

import net.qlemon.data.CalendarData;
import net.qlemon.data.CalendarGroup;
import net.qlemon.data.CalendarInfo;
import net.qlemon.data.GTCalendar;
import net.qlemon.manager.MainManager;
import android.R.menu;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Choreographer.FrameCallback;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

	public static final int CALENDAR_INDEX = 0;
	public static final int TIMELINE_INDEX = 1;
	public static final int MARKET_INDEX = 2;
	public static final int MYCAL_INDEX = 3;
	public static final int FRIEND_INDEX = 4;

	public int currentTabIndex;

	Fragment calendarFmt, timelineFmt, marketFmt, myCalFmt, friendFmt;
	Fragment lastFragment = null;

	private Toolbar mToolbar;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView menuList, disPlayList;

	public MainManager mMgr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//set data 
		mMgr = MainManager.getInstance(MainActivity.this);

		
		//set layout 
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		menuList = (ListView) findViewById(R.id.menu_drawer);
		disPlayList = (ListView) findViewById(R.id.display_drawer);

		if (mToolbar != null) {
			mToolbar.setTitle("");
			// mToolbar.setSubtitle("Test App");
			// mToolbar.setNavigationIcon(R.drawable.ic_launcher);
			setSupportActionBar(mToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		
		//get Shared Preference as first access
		SharedPreferences accessPref = getSharedPreferences("access_pref", 0);
		if(accessPref.getBoolean("isFirst", true)) {
			accessPref.edit().putBoolean("isFirst", false).apply();
			fragmentReplace(MARKET_INDEX);
		}
		else {
			fragmentReplace(TIMELINE_INDEX);
		}
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				mToolbar, R.string.app_name, R.string.app_name) {
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		MenuListAdapter menuAdapter = new MenuListAdapter(MainActivity.this);
		menuList.setAdapter(menuAdapter);
		menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mDrawerLayout.closeDrawer(menuList);
			}
		});
		
		
		//temp item add
		List<CalendarGroup> list = new ArrayList<CalendarGroup>();
		CalendarGroup item = new CalendarGroup("ID", "초등일정", "aa", false, false, false, 0);
		list.add(item);
		list.add(item);
		CalendarGroupListAdapter groupListAdapter = new CalendarGroupListAdapter(MainActivity.this, list);
//		CalendarGroupListAdapter groupListAdapter = new CalendarGroupListAdapter(MainActivity.this, mMgr.scheduleMgr.calGroups);
		
		disPlayList.setAdapter(groupListAdapter);
		disPlayList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						mDrawerLayout.closeDrawer(disPlayList);
					}
				});
		//test code
//		CalendarInfo info = new CalendarInfo("5",1,1,1,"t","desc",10,"100won",20140812,20140813,0,"me","you","none",false,0,"1.0","wow",0,0,false,false, "aa", "bb", "cc");
//		List<CalendarData> data = new ArrayList<CalendarData>();
//		GTCalendar gtCal = new GTCalendar(info, data);
//		mMgr.parser.dataToXml(gtCal);
//		mMgr.scheduleMgr.dbHelper.insertCalendarInfo(info);
//		CalendarData cData1 = new CalendarData("1", "5", "WTF", 1, 1, 1, 1400, 1800, 5, 0, 0, "Hello", "Option", "Memo", "Link", "LOC", "Special", false, "ICON");
//		CalendarData cData2 = new CalendarData("2", "5", "HHH", 1, 1, 1, 1400, 1800, 5, 0, 0, "POPIPOIP", "Option", "Memo", "Link", "LOC", "Special", false, "ICON");
//		List<CalendarData> cData = new ArrayList<CalendarData>();
//		cData.add(cData2);
//		cData.add(cData1);
//		mMgr.scheduleMgr.dbHelper.insertCalendarData(cData);
//		List<GTCalendar> gtCalendars = mMgr.scheduleMgr.dbHelper.selectAllGTCalendars();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Navigation 메뉴 아이콘 모양을 결정?
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onDisPlayClick(View v) {
		mDrawerLayout.openDrawer(disPlayList);
	}

	public void onTabClick(View v) {
		Log.d("JDI", "tab click");
		switch (v.getId()) {
		case R.id.calendar_tab:
			fragmentReplace(CALENDAR_INDEX);
			break;
		case R.id.timeline_tab:
			fragmentReplace(TIMELINE_INDEX);
			break;
		case R.id.market_tab:
			fragmentReplace(MARKET_INDEX);
			break;
		case R.id.mycal_tab:
			fragmentReplace(MYCAL_INDEX);
			break;
		case R.id.friend_tab:
			fragmentReplace(FRIEND_INDEX);
			break;

		default:
			break;
		}
	}

	public void fragmentReplace(int reqNewFragmentIndex) {

		// replace fragment
		final FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();

		if (lastFragment != null) {
			ft.hide(lastFragment);
		}

		switch (reqNewFragmentIndex) {
		case CALENDAR_INDEX:
			if (calendarFmt == null) {
				calendarFmt = new CalendarFragment();
				ft.add(R.id.container, calendarFmt);
				lastFragment = calendarFmt;
			} else {
				ft.show(calendarFmt);
				lastFragment = calendarFmt;
			}
			break;
		case TIMELINE_INDEX:
			if (timelineFmt == null) {
				timelineFmt = new TimelineFragment();
				ft.add(R.id.container, timelineFmt);
				lastFragment = timelineFmt;
			} else {
				ft.show(timelineFmt);
				lastFragment = timelineFmt;
			}
			break;
		case MARKET_INDEX:
			if (marketFmt == null) {
				marketFmt = new MarketFragement();
				ft.add(R.id.container, marketFmt);
				lastFragment = marketFmt;
			} else {
				ft.show(marketFmt);
				lastFragment = marketFmt;
			}
			break;
		case MYCAL_INDEX:
			if (myCalFmt == null) {
				myCalFmt = new MyCalFragment();
				ft.add(R.id.container, myCalFmt);
				lastFragment = myCalFmt;
			} else {
				ft.show(myCalFmt);
				lastFragment = myCalFmt;
			}
			break;
		case FRIEND_INDEX:
			if (friendFmt == null) {
				friendFmt = new FriendFragment();
				ft.add(R.id.container, friendFmt);
				lastFragment = friendFmt;
			} else {
				ft.show(friendFmt);
				lastFragment = friendFmt;
			}
			break;
		}
		
		ft.commit();

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK)
		{
			fragmentReplace(TIMELINE_INDEX);
		}
	}
	
	public void onBackPressed() {
		if(mDrawerLayout.isDrawerOpen(menuList)) 
			mDrawerLayout.closeDrawer(menuList);
		else if(mDrawerLayout.isDrawerOpen(disPlayList))
			mDrawerLayout.closeDrawer(disPlayList);
		else {
			finish();
		}
	};
}
