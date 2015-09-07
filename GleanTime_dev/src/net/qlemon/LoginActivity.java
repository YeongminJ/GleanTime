package net.qlemon;

import net.qlemon.data.Define;
import net.qlemon.dialog.JoinDialog;
import net.qlemon.dialog.LoginDialog;
import net.qlemon.manager.IMainCallback;
import net.qlemon.manager.MainManager;
import net.qlemon.utils.QLog;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	MainManager mMgr;

	Button signUpBtn;
	EditText loginView, pwdView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		mMgr = MainManager.getInstance(LoginActivity.this);

		// set UI
		((Button) findViewById(R.id.signup_btn)).setOnClickListener(this);
		((Button) findViewById(R.id.login_btn)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signup_btn:
			joinProcess();
			break;
		case R.id.login_btn:
			final LoginDialog dialog = new LoginDialog(LoginActivity.this);
			dialog.setLoginBtnListener(new OnClickListener() {
				public void onClick(View v) {
					String email = dialog.getEmailAddr();
					String pwd = dialog.getPwd();
					if (email != null && pwd != null && email.length() > 0
							&& pwd.length() > 0) {
						if(dialog.checkEmail()) {						
							Bundle parameter = new Bundle();
							parameter.putString(Define.OS, "ios");
							
							//test
							parameter.putString(Define.LOGIN_ID, "test@gmail.com");
							parameter.putString(Define.LOGIN_PWD, "1111");							
//							parameter.putString(Define.LOGIN_ID, email);
//							parameter.putString(Define.LOGIN_PWD, pwd);
							
							mMgr.requestLogin(parameter, new IMainCallback() {
								@Override
								public void onCallback(boolean result, Object data) {
									if (result) {
										startActivity(new Intent(LoginActivity.this, MainActivity.class));
										dialog.dismiss();
										finish();
									} else {
										QLog.e("JDI", "Login Error");
									}
								}
							});
						}
						else {
							Toast.makeText(LoginActivity.this, getString(R.string.email_invalid), Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(LoginActivity.this, getString(R.string.login_data_empty), Toast.LENGTH_SHORT).show();
					}
				}
			});
			dialog.setFindPwdBtnListener(new OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			dialog.setRegisterBtnListener(new OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();					
					joinProcess();
				}
			});
			dialog.show();
			break;
		}
	}

	public void joinProcess() {
		final JoinDialog joinDialog = new JoinDialog(LoginActivity.this);
		joinDialog.setRegisterBtnListener(new OnClickListener() {						
			public void onClick(View v) {
				if(joinDialog.isNotEmpty()) {
					if(joinDialog.checkEmail()) {
						if(joinDialog.isPasswordSame()) {
							final String email = joinDialog.getEmailAddr();
							final String pwd = joinDialog.getPwd();
							String nickname = joinDialog.getNickName();
							
							Bundle parameter = new Bundle();
							parameter.putString(Define.SIGNUP_EMAIL, email);
							parameter.putString(Define.SIGNUP_PWD, pwd);
							parameter.putString(Define.SIGNUP_NICKNAME, nickname);
							parameter.putString(Define.SIGNUP_TYPE, "0");	// 0 : qlemon , 1 : facebook, 2....
							
							mMgr.requestJoin(parameter, new IMainCallback() {
								
								@Override
								public void onCallback(boolean result, Object data) {
									if(result) {
										startActivity(new Intent(LoginActivity.this, MainActivity.class));
										finish();
										joinDialog.dismiss();
										
										Bundle parameter = new Bundle();
										parameter.putString(Define.OS, "ios");
										parameter.putString(Define.LOGIN_ID, email);
										parameter.putString(Define.LOGIN_PWD, pwd);
										mMgr.requestLogin(parameter, new IMainCallback() {
											@Override
											public void onCallback(boolean result, Object data) {
												if (result) {
													startActivity(new Intent(LoginActivity.this, MainActivity.class));
													joinDialog.dismiss();
													finish();
												} else {
													QLog.e("JDI", "Login Error");
												}
											}
										});
									}
									else {
										QLog.e("JDI", "Join Error");
									}
								}
							});
							
						}
						else {
							//Password not match
							Toast.makeText(LoginActivity.this, getString(R.string.password_not_match), Toast.LENGTH_SHORT).show();
						}
					}
					else {
						//Email Invalid
						Toast.makeText(LoginActivity.this, getString(R.string.email_invalid), Toast.LENGTH_SHORT).show();
					}
				}
				else {
					// empty 
					Toast.makeText(LoginActivity.this, getString(R.string.signup_data_empty), Toast.LENGTH_SHORT).show();
				}
			}
		});
		joinDialog.show();
	}
}
