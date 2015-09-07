package net.qlemon.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.qlemon.http.HttpResource;
import net.qlemon.http.HttpResource.HttpMethod;
import net.qlemon.http.IHttpCallback;
import net.qlemon.utils.QLog;
import android.content.Context;
import android.os.Bundle;

public class HttpManager {

	String TAG = "HttpManager";
	
	private Context _context;
	private URL _url;
	private Bundle _parameter;
	private IHttpCallback _calback;
	private String _contentEncoding;
	private HttpMethod _method;

	public HttpManager(Context context, String url, Bundle parameter, HttpMethod method, IHttpCallback calback) throws Exception {
		_context = context;
		_url = new URL(url);
		_parameter = parameter;
		_calback = calback;
		_contentEncoding = HttpResource.CONTENT_ENCODING_UTF8;
		_method = method;
		new Thread(requestRun).start();
	}

	Runnable requestRun = new Runnable() {

		@Override
		public void run() {
			if (_url == null) {
				// _calback.onResponse(HttpStatus.SC_ACCEPTED, response);
				return;
			} else {
				try {
					
					/*
					 * https Connection
					trustAllHosts();
					
					HttpsURLConnection connection = (HttpsURLConnection) _url.openConnection();
					connection.setHostnameVerifier(new HostnameVerifier() {						
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					});
					*/
					
					HttpURLConnection connection;
					
					if(_method == HttpMethod.GET) {
						StringBuffer sb = new StringBuffer();
						for(String key : _parameter.keySet())
						{
							sb.append(key).append("=").append(_parameter.get(key).toString()).append("&");
						}
						if(sb.length() > 0) {
							String val = sb.substring(0, sb.length()-1);
							String str = _url.toString() + "?" + val;
							_url = new URL(str);
						}
						connection = (HttpURLConnection) _url.openConnection();
						connection.setRequestMethod("GET");						
					}
					else {
						connection = (HttpURLConnection) _url.openConnection();
						connection.setRequestMethod("POST");
					}
					
					connection.connect();
					
					if(_method == HttpMethod.POST) {
						StringBuffer sb = new StringBuffer();
						for(String key : _parameter.keySet())
						{
							sb.append(key).append("=").append(_parameter.get(key).toString()).append("&");
						}
						if(sb.length() > 0) {
							String val = sb.substring(0, sb.length()-1);
							QLog.d("JDI", "send URI = " + _url + " send Msg = " + val);
							
							OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), _contentEncoding);
							writer.write(val);
							writer.flush();
							writer.close();
						}
					}
					QLog.d("JDI", ":" + _url);
					int resCode = connection.getResponseCode();
					QLog.i("JDI", "resCode = " + resCode);
					StringBuffer resp = new StringBuffer();
					BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), _contentEncoding));
					String line;
					while( (line = br.readLine()) != null) {
						resp.append(line);
					} 
					br.close();
					
					//callback
					_calback.onResponse(resCode, resp.toString(), _url.toString());
				} catch (IOException e) {
					e.printStackTrace();
					QLog.e(TAG, e.getMessage());
					_calback.onResponse(400, "error", _url.toString());
				}
			}
		}
	};
	
	private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
