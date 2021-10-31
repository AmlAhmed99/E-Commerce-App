package com.example.ecommerce;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText birthdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final ImageView imageView = (ImageView)findViewById(R.id.log_btn);
        final EditText username=(EditText) findViewById(R.id.username);
        final EditText password=(EditText) findViewById(R.id.password);
        final EditText name=(EditText) findViewById(R.id.name);
         birthdate=(EditText) findViewById(R.id.birthdate);
        final EditText gender=(EditText) findViewById(R.id.gender);
        final EditText job=(EditText) findViewById(R.id.job);

        //...................................................
        final DatabaseHelper obj=new DatabaseHelper(this);
        //....................................................
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamestr=username.getText().toString();
                String passwordstr=password.getText().toString();
                String namestr=name.getText().toString();
                String genderstr=gender.getText().toString();
                String birthdatestr=birthdate.getText().toString();
                String jobstr=job.getText().toString();

                if(usernamestr.equals("")||passwordstr.equals("")||namestr.equals("")||genderstr.equals("")||birthdatestr.equals("")||jobstr.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "please enter data", Toast.LENGTH_LONG).show();
                }
                else {
                    obj.addperson(namestr,usernamestr,passwordstr,genderstr,birthdatestr,jobstr);
                    Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    name.getText().clear();
                    username.getText().clear();
                    password.getText().clear();
                    birthdate.getText().clear();
                    gender.getText().clear();
                    job.getText().clear();

                }
            }
        });

        final ImageView imageView2 = (ImageView)findViewById(R.id.login);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDatePickerDialog();
            }
        });


    }

    //....................................................
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date =  month + "/" + dayOfMonth + "/" + year;
        birthdate.setText(date);
    }

    //....................................................


}
