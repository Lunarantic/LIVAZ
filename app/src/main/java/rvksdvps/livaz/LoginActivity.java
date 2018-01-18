package rvksdvps.livaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    TextView link_signUp;
    Button logginBtn;
    EditText username,password;
    String userName,pwd;
    WardrobeDBHelper wardrobeDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wardrobeDBHelper = new WardrobeDBHelper(getApplicationContext());

        link_signUp= (TextView) findViewById(R.id.link_signup);
        logginBtn= (Button) findViewById(R.id.btn_login);
        username= (EditText) findViewById(R.id.input_UserName);
        password= (EditText) findViewById(R.id.input_password);

        link_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });


        logginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userName = username.getText().toString();
                pwd = password.getText().toString();
                String PrePass="";
                try {
                    PrePass = SignUpActivity.sharedPreferences.getString(userName, "w");
                }catch (NullPointerException e)
                {
                    Toast.makeText(getApplicationContext(), "Incorrect Username", Toast.LENGTH_SHORT).show();
                }
                if ((userName.length() == 0) && (pwd.length() == 0)) {
                    username.setError("Username is compulsory");
                    password.setError("Password is compulsory");
                } else if (pwd.equals(PrePass)) {

                    Toast.makeText(getApplicationContext(), "Successfull!!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(intent1);
                    finish();
                } else
                {
                    Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");


                }

                //Intent intent1 = new Intent(getApplicationContext(), Dashboard.class);
                //startActivity(intent1);
                //finish();

            }
        });




    }
}
