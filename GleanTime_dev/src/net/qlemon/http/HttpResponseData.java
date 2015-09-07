package net.qlemon.http;

public class HttpResponseData {
	public int _httpStatus;
	public String _response;
	
	public HttpResponseData(int httpstatus, String response)
	{
		this._httpStatus = httpstatus;
		this._response = response;
	}
}
