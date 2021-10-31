package com.example.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences mprefs;
    private static final String PREFS_NAME="PrefsFile";
     private EditText username;
    private EditText password;
    private CheckBox rememberme;


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     /////////////////////////////////////////////////////
        mprefs=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        getprefrencesData();
    //////////////////////////////////////////////////////


         rememberme=(CheckBox) findViewById(R.id.checkBox3);
        final TextView forgetpassword=(TextView) findViewById(R.id.forgetpassword);
        username=(EditText) findViewById(R.id.email_username);
         password=(EditText) findViewById(R.id.passwordd);
        final DatabaseHelper obj=new DatabaseHelper(this);
        final ImageView imageView = (ImageView)findViewById(R.id.reg_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamestr1=username.getText().toString();
                String passwordstr1=password.getText().toString();

                if(usernamestr1.equals("")||passwordstr1.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "please enter data", Toast.LENGTH_LONG).show();
                }
                else
                    {
                    boolean var=obj.checkUser(usernamestr1);
                    if(var==true) {
                        Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();
                        //prefrence
                        if(rememberme.isChecked())
                        {
                            Boolean boolischecked=rememberme.isChecked();
                            SharedPreferences.Editor editor=mprefs.edit();
                            editor.putString("pref_name",usernamestr1);
                            editor.putString("pref_pass",passwordstr1);
                            editor.putBoolean("pref_check",boolischecked);
                            //to save daata
                            editor.apply();
                            Toast.makeText(getApplicationContext(), "setting have been saved", Toast.LENGTH_LONG).show();


                        }
                        else
                        {
                            mprefs.edit().clear().apply();
                        }

                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        username.getText().clear();
                        password.getText().clear();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "please register first", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


        final ImageView imageView2 = (ImageView)findViewById(R.id.register);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
                startActivity(intent);

            }
        });


    }
///////////////////////////////////////////////
    //load ingo in text view
    private void getprefrencesData() {
        username=(EditText) findViewById(R.id.email_username);
        password=(EditText) findViewById(R.id.passwordd);
        rememberme=(CheckBox) findViewById(R.id.checkBox3);
        SharedPreferences sp=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        if(sp.contains("pref_name"))
        {
            String name=sp.getString("pref_name","not found");
            username.setText(name);

        }
        if(sp.contains("pref_pass"))
        {
            String pass=sp.getString("pref_pass","not found");
            password.setText(pass);

        }
        if(sp.contains("pref_check"))
        {
            boolean check=sp.getBoolean("pref_check",false);
            rememberme.setChecked(check);

        }
    }
    ////////////////////////////////////////


}
