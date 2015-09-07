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

public class JoinDialog extends Dialog {

	private Button registerBtn;
	private EditText emailView, pwdView, pwdConfirmView, nicknameView;
	
	public JoinDialog(Context context) {
		super(context);
		
		this.setCanceledOnTouchOutside(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		setContentView(R.layout.dialog_join);
		
		registerBtn = (Button) findViewById(R.id.register_btn);
		emailView = (EditText) findViewById(R.id.mail_view);
		pwdView = (EditText) findViewById(R.id.pwd_view);
		pwdConfirmView = (EditText) findViewById(R.id.pwd_confirm_view);
		nicknameView = (EditText) findViewById(R.id.nickname_view);
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
	
	private String getConfirmPwd() {
		return pwdConfirmView.getText().toString();
	}
	
	public String getNickName() {
		return nicknameView.getText().toString();
	}
	
	public boolean isPasswordSame() {
		boolean result = false;
		
		if(pwdView.getText().toString().equals(pwdConfirmView.getText().toString())) result = true;
		
		return result;
	}
	
	public boolean checkEmail()
	{
		String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		Pattern p = Pattern.compile(mail);
		Matcher m = p.matcher(emailView.getText());
		return m.matches();
	}
	
	//check Edit text empty
	public boolean isNotEmpty() {
		boolean result = false; 
		
		if(getEmailAddr().length() > 0 && getPwd().length() > 0 && getNickName().length() > 0 && getConfirmPwd().length() > 0) result = true;
		
		return result;
	}
}
