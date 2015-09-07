package net.qlemon.http;

public class HttpResource {
	
	public static String ROOT = "http://qlemon.net:8000/";
	public static String ROOT_SEQURE = "https://qlemon.net:8000/";
	
	public static String LOGIN_URL = ROOT + "fcal/api/signin";
	public static String JOIN_URL = ROOT + "fcal/api/signup";
	public static String MARKET_SUMMARY_URL = ROOT + "fcal/api/sharecalendar/summary";
	
	
	public static final int HTTP_CONNECTION_TIMEOUT = 1500;
	public static final int HTTP_READ_TIMEOUT = 10000;
	
	public static final String CONTENT_ENCODING_UTF8 = "UTF-8";
	public static final String CONTENT_ENCODING_EUC_KR = "EUC-KR";
	
	public static enum HttpMethod {GET, POST};
}
