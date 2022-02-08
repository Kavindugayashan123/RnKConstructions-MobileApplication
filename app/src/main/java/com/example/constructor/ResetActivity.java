package com.example.constructor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class ResetActivity extends AppCompatActivity {

    private ImageView imgClose,image;
    private TextView username;
    private EditText pass,repass;
    private Button btnconform;
    private DataBaseHelper myDB;
    AwesomeValidation awesomeValidation;
    Cursor cursor;
    String txt;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        getSupportActionBar().hide();


        image = findViewById(R.id.image);
        username = findViewById(R.id.name);
        pass = findViewById(R.id.password_reset);
        repass = findViewById(R.id.repassword_reset);
        btnconform = findViewById(R.id.btnconform);
        imgClose = findViewById(R.id.btnClose);


        Window window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.myStatusBar));

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.password_reset, RegexTemplate.NOT_EMPTY, R.string.not_null);
        awesomeValidation.addValidation(this, R.id.repassword_reset, RegexTemplate.NOT_EMPTY, R.string.not_null);


        myDB = new DataBaseHelper(this);


        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        txt = username.getText().toString();
        txt = "'" + txt + "'";


        String POSTS_SELECT_QUERY =
                String.format("Select * from Register  WHERE Rname = " + txt);

        SQLiteDatabase db = myDB.getReadableDatabase();

        try {
            cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

            if (cursor.moveToFirst()) {
                do {

                    image.setImageURI(Uri.parse(cursor.getString(3)));


                } while (cursor.moveToNext());
            }

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }


            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


            btnconform.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (awesomeValidation.validate()) {

                        String user = username.getText().toString();
                        String password = pass.getText().toString();
                        String repassword = repass.getText().toString();
                        if (password.equals(repassword)) {
                            myDB.updatePassword(user, password);
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                            Toast.makeText(ResetActivity.this, "Password Updated successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetActivity.this, "password not matching", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }

    }


}