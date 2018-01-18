package rvksdvps.livaz;

/**
 * Created by 150840521010 on 18-01-2016.
        */
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 150840521010 on 18-01-2016.
 */
public class SignUpActivity extends AppCompatActivity
{
    TextView link_login;
    EditText editUsername,editPwd,editConPwd;

    Button signUpBtn;
    static SharedPreferences sharedPreferences;

    String userName, passWord,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editUsername = (EditText) findViewById(R.id.input_SignUp_UserName);
        editPwd = (EditText) findViewById(R.id.input_SignUP_password);
        editConPwd = (EditText) findViewById(R.id.input_SignUP_Conf_password);
        signUpBtn= (Button) findViewById(R.id.btn_signup);
        link_login= (TextView) findViewById(R.id.link_login);

        //creating file for sharedPrefernce
        sharedPreferences=getSharedPreferences("File1",MODE_PRIVATE);

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });





        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=editUsername.getText().toString();
                passWord=editPwd.getText().toString();
                confirmPassword=editConPwd.getText().toString();

                if(passWord.equals(confirmPassword))
                {
                    sharedPreferences.edit().putString(userName, passWord).commit();
                    Toast.makeText(getApplicationContext(), "Successfull!!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();

                }else
                {
                    editConPwd.setText("");
                    editPwd.setText("");
                    Toast.makeText(getApplicationContext(),"Re-Enter Password",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}