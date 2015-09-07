package net.qlemon;

import net.qlemon.utils.XmlParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimelineFragment extends Fragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		//test
				 
		String response;
				
		response = "{	'data' : 	{'count':6,'item':		[			{'calendar_list':				[					{'calendar':'<QLShareCalendar><Ver>5</Ver><Type>1</Type><SubType>0</SubType><Category>1</Category><Title>초등고학년남자아이일정</Title><Description>초등학교 고학년용 남자아이  일정표 입니다</Description>'}				]			}		]	}}";
		
		XmlParser parser = new XmlParser();
		//First Separate JSONObject & inner XML Data
		
		
//		try {
//			
//			JSONObject obj = new JSONObject(response);			  
//			JSONObject dataObj = obj.getJSONObject("jsontest");
//			
//			//mData.setAdURL(adURL);
//			JSONArray itemArray = dataObj.getJSONArray("item");
//			for(int i = 0; i < itemArray.length(); i++)
//			{
//				JSONArray calArray = itemArray.getJSONObject(i).getJSONArray("calendar_list");								
//				for(int j = 0; j < calArray.length(); j++) {
//					JSONObject calObj = calArray.getJSONObject(j);									
//					GTCalendar gtItem = parser.parseXmlData(calObj.getString("calendar"));					
//				}
//			}
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.fragment_timeline, container, false);		
		
		
		return root;
	}
}
