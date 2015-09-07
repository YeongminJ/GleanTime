package net.qlemon;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MarketFragement extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Log.d("JDI", "oncreate MarketFragement");
		
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.fragment_market, container, false);		
		
		
		return root;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Log.d("JDI", "onResume MarketFragement");
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		Log.e("JDI", "onAttach MarketFragement");
	}
}
