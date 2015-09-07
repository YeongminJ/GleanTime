package net.qlemon;

import org.json.JSONException;
import org.json.JSONObject;

import net.qlemon.utils.QLog;
import net.qlemon.utils.XmlParser;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarFragment extends Fragment {
	
	Button btn;
	LinearLayout containerView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {				
		// TODO Auto-generated method stub
		
		QLog.d("JDI", "oncreate CalendarFragment");
		
		View root = inflater.inflate(R.layout.fragment_calendar, container, false);		
		containerView = (LinearLayout)root.findViewById(R.id.container);
		btn = (Button)root.findViewById(R.id.cal_btn);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//test code
				getActivity().startActivityForResult(new Intent(getActivity(), CalendarInfoActivity.class), 100);
//				TextView newView = new TextView(getActivity());
//				newView.setText("Hello");
//				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//				newView.setLayoutParams(params);
//				containerView.addView(newView);
				
//				String xmlData = "<QLShareCalendar><Ver /><Type>1</Type><SubType>0</SubType><Category>1</Category><Title>초등고학년남자아이일정</Title><Description>초등학교 고학년용 남자아이  일정표 입니다</Description>";
//				new XmlParser().parseXmlData(xmlData);
			}
		});;
		
		return root;
	}
}
