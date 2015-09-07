package net.qlemon.manager;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import net.qlemon.data.Define;
import net.qlemon.data.GTCalendar;
import net.qlemon.data.MarketData;
import net.qlemon.http.HttpResource;
import net.qlemon.http.IHttpCallback;
import net.qlemon.utils.QLog;
import net.qlemon.utils.XmlParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class MainManager implements ManagerCallback{

	public Context _context;
	public static MainManager mainMgr;
	public DataManager dataMgr;
	public ScheduleManager scheduleMgr;
	public XmlParser parser;
		
	//singleTone
	public static MainManager getInstance(Context context)
	{
		if(mainMgr == null) {
			mainMgr = new MainManager(context);
		}
		return mainMgr;
	}
	
	public MainManager(Context context) {
		_context = context;		
		dataMgr = new DataManager();
		scheduleMgr = new ScheduleManager(context);
		parser = new XmlParser();
	}	
	
	/**
	 * 로그인
	 * In - os : ios, id : test@gmail.com, password : 1111, Callback
	 * Out - {
				  "data": {
				    "count": 1, 
				    "item": 
				    [
				        {
				            "app_link": "http://naver.com", 
				            "app_version": "1.0.0", 
				            "session_id": "dGVzdEBnbWFpbC5jb207MTExMQ=="
				        }
				    ]
				  }, 
					  "meta": {
					    "msg": "Success"
				  }
			}
	   요청 방식 - GET
	 * 
	 * **/
	public void requestLogin(final Bundle parameter, final IMainCallback mainCallback) {			
		
		try {
			new HttpManager(_context, HttpResource.LOGIN_URL, parameter, HttpResource.HttpMethod.GET, new IHttpCallback() {
				
				@Override
				public void onResponse(int httpStauts, String response, String requestURL) {
					// TODO Auto-generated method stub
					if(httpStauts == HttpURLConnection.HTTP_OK) {		
						try {
							//save sessionId, ID, PWD to SharedPreference
							String sessionID = new JSONObject(response).getJSONObject("data")
									.getJSONArray("item").getJSONObject(0).getString(Define.SESSION_ID);
							Editor editor = _context.getSharedPreferences(Define.LOGIN_PREF, 0).edit();
							editor.putString(Define.LOGIN_ID, parameter.getString(Define.LOGIN_ID));
							editor.putString(Define.LOGIN_PWD, parameter.getString(Define.LOGIN_PWD));
							editor.putString(Define.SESSION_ID, sessionID);
							editor.apply();
							
							QLog.i("JDI", "session ID : " + sessionID);
							
							//make Callback by msg
							mainCallback.onCallback(true, "");	
						} catch (JSONException e) {
							e.printStackTrace();
							mainCallback.onCallback(false, "");	
						}
											
					}
					else {
						mainCallback.onCallback(false, "");	
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원 가입 
	 * In - email : test@gmail.com, password : 1111, nickname : niki, type : 0(qlemon) or 1(facebook), Callback
	 * Out - {
				  "meta": {
				    "msg": "Success"
				  }
			 }
	   요청 방식 - GET
	 * **/
	public void requestJoin(Bundle parameter, final IMainCallback mainCallback) {
		try {
			new HttpManager(_context, HttpResource.JOIN_URL, parameter, HttpResource.HttpMethod.GET, new IHttpCallback() {
				
				@Override
				public void onResponse(int httpStauts, String response, String requestURL) {
					// TODO Auto-generated method stub
					boolean result = false;
					if(httpStauts == HttpURLConnection.HTTP_OK) {		
						if(response.contains("Success")) result = true;						
					}
					mainCallback.onCallback(result, "");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 마켓 Summary 가져오기
	 * In - email : test@gmail.com, password : 1111, nickname : niki, type : 0(qlemon) or 1(facebook), Callback
	 * Out - {
				  "data": {
				    "count": 2,
				    "item": [
				      {
				        "category": 1,
				        "count": 2,
				        "calendar_list": [
				          {
				            "calendar": xml string,
				            "id": 1
				          },
				          {
				            "calendar": xml string,
				            "id": 2
				          }
				        ]
				      }
				    ]
			    }
			}
		요청 방식 - GET
	 * **/
	public void requestMarketData(Bundle parameter, IMainCallback mainCallback) {
		final MarketData mData = new MarketData();
		final List<GTCalendar> mList = new ArrayList<GTCalendar>();
		
		try {
			new HttpManager(_context, HttpResource.MARKET_SUMMARY_URL, parameter, HttpResource.HttpMethod.GET, new IHttpCallback() {
				
				@Override
				public void onResponse(int httpStauts, String response, String requestURL) {
					// TODO Auto-generated method stub
					if(httpStauts == HttpURLConnection.HTTP_OK) {
						
						//First Separate JSONObject & inner XML Data
						try {
							JSONObject obj = new JSONObject(response);
							JSONObject dataObj = obj.getJSONObject("data");
							mData.setCategoryCnt(Integer.parseInt(dataObj.optString("count")));
							//mData.setAdURL(adURL);
							JSONArray itemArray = dataObj.getJSONArray("item");
							for(int i = 0; i < itemArray.length(); i++)
							{
								JSONArray calArray = itemArray.getJSONObject(i).getJSONArray("calendar_list");								
								for(int j = 0; j < calArray.length(); j++) {
									JSONObject calObj = calArray.getJSONObject(j);									
									GTCalendar gtItem = parser.parseXmlData(calObj.getString("calendar"));
									mList.add(gtItem);
								}
							}
							
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		mData.setMarketCal(mList);
		dataMgr.mData = mData;
		mainCallback.onCallback(true, "");
	}
	
	/**
	 * 스케줄 Load 하기
	 * - DB, OtherCalendar (google, os calendar), AutoUpdate 의 스케줄을 불러온다. 
	 * In : callback
	 * Out : None
	 * **/
	public void requestLoadSchedule(IMainCallback mainCallback) {
		//1. load DB Schedule, DB calendarGroup
		boolean shareCalResult = scheduleMgr.loadSchedule();
		//2. load OtherCalendar Schedule
		//3. AutoUpdate DB Schedule from Server then update DB
		//4. create OrderSchedule
		mainCallback.onCallback(true, "");
	}
	
	public void getIt(String calenderID)
	{
		List<GTCalendar> gtCal = dataMgr.mData.getMarketCal();
		int calendarIdx = 0;
		for(int i = 0; i < gtCal.size(); i++)
		{
			String qid = gtCal.get(i).getcInfo().getQid();
			if(qid.equals(calenderID)) {
				calendarIdx = i;
				break;
			}
		}
		scheduleMgr.addSchedule(gtCal.get(calendarIdx));
	}

	@Override
	public void processDone(RESULT_TYPE result, Object data) {
		// TODO Auto-generated method stub		
		switch (result) {
		case ADD_DONE:			
			break;
		case DELETE_DONE:
			break;
		default:
			break;
		}
	}

	
}
