package net.qlemon;

import net.qlemon.data.Define;
import net.qlemon.manager.IMainCallback;
import net.qlemon.manager.MainManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class IntroActivity extends Activity {

	public MainManager mMgr;

	TextView textView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_intro);
		// set data
		mMgr = MainManager.getInstance(IntroActivity.this);
		
		//set layout
		textView = (TextView)findViewById(R.id.intro_text);

		SharedPreferences loginPref = getSharedPreferences(Define.LOGIN_PREF, 0);
		Bundle parameter = new Bundle();
		String email = loginPref.getString(Define.LOGIN_ID, null);
		String pwd = loginPref.getString(Define.LOGIN_PWD, null);
		parameter.putString(Define.OS, "ios");
		parameter.putString(Define.LOGIN_ID, email);
		parameter.putString(Define.LOGIN_PWD, pwd);
		if(email != null && pwd != null) {
			mMgr.requestLogin(parameter, new IMainCallback() {
				
				@Override
				public void onCallback(boolean result, Object data) {
					if(result) {
						//loadSchedule
						loadSchedule();
					}
					else {
						//Popup to Login Error
					}
				}
			});
		}
		else {
			//LoginActivity gogo
			startActivity(new Intent(IntroActivity.this, LoginActivity.class));
			finish();
		}
	}
	
	public void loadSchedule() {
		mMgr.requestLoadSchedule(new IMainCallback() {
			
			@Override
			public void onCallback(boolean result, Object data) {
				// TODO Auto-generated method stub
				if(result) {
					startActivity(new Intent(IntroActivity.this, MainActivity.class));
					finish();
				}
			}
		});
	}
}
