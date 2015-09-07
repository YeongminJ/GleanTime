package net.qlemon;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListAdapter extends BaseAdapter {

	Context context;
	String[] itemList;
	int[] resourceIds;
	TypedArray iconImgs;
	
	
	private int LOGIN_ITEM_COUNT = 1;
	
	public MenuListAdapter(Context context) {		
		this.context = context; 
		itemList = context.getResources().getStringArray(R.array.menu_list);
		iconImgs = context.getResources().obtainTypedArray(R.array.menu_icon_list);		
		int iconCnt = iconImgs.length();
		resourceIds = new int[iconCnt];		
		for (int i = 0; i < iconCnt; i++) {
			resourceIds[i] = iconImgs.getResourceId(i, -1);
		}		
		
		iconImgs.recycle();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList.length + LOGIN_ITEM_COUNT;
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
		// TODO Auto-generated method stub
		MenuViewHolder holder;
		
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.menu_item, parent, false);
			
			holder = new MenuViewHolder();
			holder.iconView = (ImageView)convertView.findViewById(R.id.item_icon);
			holder.textView = (TextView)convertView.findViewById(R.id.item_text);
			holder.pointView = (ImageView)convertView.findViewById(R.id.item_point);
			
			convertView.setTag(holder);
		}
		else {
			holder = (MenuViewHolder)convertView.getTag();
		}
		
		//LOGIN MENU
		if(position == 0) {
			convertView.setBackgroundColor(Color.GRAY);
//			holder.iconView.setImageResource(resourceIds[position]);;
			holder.textView.setText("Login Account Info");			
			holder.pointView.setVisibility(View.INVISIBLE);
		}
		else {
			holder.iconView.setImageResource(resourceIds[position-1]);;
			holder.textView.setText(itemList[position-1]);
			holder.pointView.setVisibility(View.VISIBLE);
		}
		
		return convertView;
	}
	
	public class MenuViewHolder {
		public ImageView iconView, pointView;
		public TextView textView;		
	}
}
