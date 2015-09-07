package net.qlemon.http;
public interface IHttpCallback {
	public void onResponse(int httpStauts, String response, String requestURL);
}
