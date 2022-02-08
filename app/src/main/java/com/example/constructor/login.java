package com.example.constructor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.se.omapi.Session;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class login extends AppCompatActivity {

    private Button btnlogin;
    private EditText username,password;
    private TextView registertext,textforgetpass;
    AwesomeValidation awesomeValidation;
    private DataBaseHelper DB;
    Dialog dialog;



    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        Window window=getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.myStatusBar));

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.username1, RegexTemplate.NOT_EMPTY,R.string.not_null);

        awesomeValidation.addValidation(this,R.id.password1, RegexTemplate.NOT_EMPTY,R.string.not_null);

        //login Loading
        dialog=new Dialog(this);

      /*  if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.setStatusBarColor(getColor(R.color.myStatusBar));
        }
        else {
            window.setStatusBarColor(getResources().getColor(R.color.myStatusBar));
        }*/

    //Form Validations
        username =(EditText) findViewById(R.id.username1);
        password =(EditText)findViewById(R.id.password1);
        registertext=findViewById(R.id.registertext);
        textforgetpass=findViewById(R.id.textforgetpass);


        btnlogin=(Button) findViewById(R.id.btnLogin1);
        DB = new DataBaseHelper(this);



                /*Toast toast= Toast.makeText(getApplicationContext(),"hellow",Toast.LENGTH_SHORT);
                toast.show();*/
        textforgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),passwordactivity.class);
                startActivity(intent);

            }
        });

        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),addRegister.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    String user = username.getText().toString().trim();
                    String pass = password.getText().toString().trim();

                   /* if (user.equals("") || pass.equals(""))
                        Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else {*/
                        Boolean checkup = DB.checkUsernameassword(user, pass);
                        if (checkup == true) {

                            openDilogLoding();
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    closeDilogLoding();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    intent.putExtra("username", user);
                                    startActivity(intent);
                                }
                            },3000);
                            //Toast.makeText(login.this, "sign in successful", Toast.LENGTH_SHORT).show();
                            }
                        else {
                            Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();


                        }
                    }
                }

        });


    }
    private void openDilogLoding(){
        dialog.setContentView(R.layout.custom_dilog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }
    private void closeDilogLoding(){
        dialog.dismiss();
    }
}

