package net.qlemon.dialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.qlemon.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class LoginDialog extends Dialog {

	private Button loginBtn, findPwdBtn, registerBtn;
	private EditText emailView, pwdView;
	
	public LoginDialog(Context context) {
		super(context);
		
		this.setCanceledOnTouchOutside(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		setContentView(R.layout.dialog_login);
		
		loginBtn = (Button) findViewById(R.id.login_btn);
		findPwdBtn = (Button) findViewById(R.id.findpwd_btn);
		registerBtn = (Button) findViewById(R.id.register_btn);
		emailView = (EditText) findViewById(R.id.mail_view);
		pwdView = (EditText) findViewById(R.id.pwd_view);
	}		

	public void setLoginBtnListener(View.OnClickListener loginListener) {
		loginBtn.setOnClickListener(loginListener);
	}
	
	public void setFindPwdBtnListener(View.OnClickListener findListener) {
		findPwdBtn.setOnClickListener(findListener);
	}
	
	public void setRegisterBtnListener(View.OnClickListener registerListener) {
		registerBtn.setOnClickListener(registerListener);
	}
	
	public String getEmailAddr() {		
		return emailView.getText().toString();
	}
	
	public String getPwd() {
		return pwdView.getText().toString();
	}
	
	public boolean checkEmail()
	{
		String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		Pattern p = Pattern.compile(mail);
		Matcher m = p.matcher(emailView.getText());
		return m.matches();
	}
}
