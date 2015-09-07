package net.qlemon.utils;

import android.util.Log;

public class QLog {
	
	static boolean DEBUG = true;
	
	public static void v(String tag, String description) {
		if(DEBUG) Log.v(tag, description);
	}
	
	public static void d(String tag, String description) {
		if(DEBUG) Log.d(tag, description);
	}
	
	public static void i(String tag, String description) {
		if(DEBUG) Log.i(tag, description);
	}
	
	public static void w(String tag, String description) {
		if(DEBUG) Log.w(tag, description);
	}

	public static void e(String tag, String description) {
		if(DEBUG) Log.e(tag, description);
	}
	
}
