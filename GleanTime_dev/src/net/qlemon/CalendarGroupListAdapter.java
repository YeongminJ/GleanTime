package net.qlemon;

import java.util.List;

import net.qlemon.data.CalendarGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CalendarGroupListAdapter extends BaseAdapter {

	Context context;
	List<CalendarGroup> list;
	
	public CalendarGroupListAdapter(Context context, List<CalendarGroup> list) {		
		this.context = context; 
		this.list = list;		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		DisplayMenuHolder holder;
		
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.calendar_group_item, null);
			
			holder = new DisplayMenuHolder();
			
			holder.applyBtn = (Button) convertView.findViewById(R.id.apply_btn);
			holder.showBtn = (Button) convertView.findViewById(R.id.show_btn);
			holder.alarmBtn = (Button) convertView.findViewById(R.id.alarm_btn);
			holder.iconView = (ImageView) convertView.findViewById(R.id.cal_icon);
			holder.textView = (TextView) convertView.findViewById(R.id.cal_name);
			
			convertView.setTag(holder);
		}
		else {
			holder = (DisplayMenuHolder) convertView.getTag();
		}
		
		CalendarGroup item = list.get(position);
		
		//set btn state
		if(item.isApply()) {}
		else {}
		if(item.isShowing()) {}
		else {}
		if(item.isAlarm()) {}
		else {}
		
		//set Icon 
//		holder.iconView.setImageDrawable(drawable);
		//set Title
		holder.textView.setText(item.getGroupName());
		
		return convertView;
	}
	
	public class DisplayMenuHolder {
		public ImageView iconView;
		public TextView textView;
		public Button applyBtn, showBtn, alarmBtn;
	}

}
