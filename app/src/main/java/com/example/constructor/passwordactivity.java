package com.example.constructor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class passwordactivity extends AppCompatActivity {


    private EditText textusername;
    private Button btnreset;
    private ImageView imgClose;
    AwesomeValidation awesomeValidation;

    private DataBaseHelper myDB;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordactivity);
        getSupportActionBar().hide();



        textusername=findViewById(R.id.textusername);
        btnreset=findViewById(R.id.btnreset);
        imgClose=findViewById(R.id.btnClose);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.textusername, RegexTemplate.NOT_EMPTY,R.string.not_null);

        Window window=getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.myStatusBar));

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        myDB = new DataBaseHelper(this);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    String username = textusername.getText().toString();
                    Boolean checkuser = myDB.checkUsername(username);

                    if (checkuser == true) {
                        Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(passwordactivity.this, "User does not exits", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}