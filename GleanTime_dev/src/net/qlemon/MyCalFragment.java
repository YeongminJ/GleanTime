package net.qlemon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyCalFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Log.d("JDI", "oncreate MyCalFragment");
		
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.fragment_mycal, container, false);		
		
		
		return root;
	}
}
