package com.example.ecommerce;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity {
    DatabaseHelper obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        final EditText username=(EditText) findViewById(R.id.emailf);
        final EditText password=(EditText) findViewById(R.id.passwordf);
        final EditText confirmpassword=(EditText) findViewById(R.id.passwordf2);
        ImageView reset=(ImageView)findViewById(R.id.reset);
         obj =new DatabaseHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamestr = username.getText().toString();
                String passwordstr = password.getText().toString();
                String passwordstr2 = confirmpassword.getText().toString();

                boolean checkuser = obj.checkUser(usernamestr);
                if (passwordstr.equals(passwordstr2)) {
                    if (checkuser == true) {

                         boolean checkpasswordupdate=obj.updatepassword(usernamestr,passwordstr);
                         if(checkpasswordupdate==true){
                             Toast.makeText(getApplicationContext(), "password updated successfully", Toast.LENGTH_LONG).show();
                         }
                         else
                         {
                             Toast.makeText(getApplicationContext(), "password not updated ", Toast.LENGTH_LONG).show();
                         }

                    }
                    else
                        {
                        Toast.makeText(getApplicationContext(), "username dosent exist?", Toast.LENGTH_LONG).show();

                       }
                }
                else
                    {

                    Toast.makeText(getApplicationContext(), "password not match", Toast.LENGTH_LONG).show();
                }
            }
        });






    }



}
