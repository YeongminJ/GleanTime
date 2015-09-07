package net.qlemon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CalendarInfoActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_calendarinfo);
	    
	}
	
	public void onClick(View v) {
		
		setResult(RESULT_OK);
		finish();
	}

}
