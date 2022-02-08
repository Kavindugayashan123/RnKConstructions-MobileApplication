package com.example.constructor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main_Intro extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout layout;
    private TextView[] dots;
    private Button btnBack,btnNext;
    private SliderAdapter sliderAdapter;
    private int mCurrentPage;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__intro);
        getSupportActionBar().hide();

        Window window=this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.myStatusBar));


        //Check if application is opened for the first time
        SharedPreferences preferences=getSharedPreferences("PREFERANCE",MODE_PRIVATE);
        String FirstTime=preferences.getString("FirstTimeInstall","");

        if(FirstTime.equals("YES")){
            //if application was opened first time
            Intent intent=new Intent(Main_Intro.this,AnimatedOpen.class);
            startActivity(intent);

        }else {
            //else
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor=preferences.edit();
            editor.putString("FirstTimeInstall","YES");
            editor.apply();
        }





        viewPager=findViewById(R.id.viewPager);
        layout=findViewById(R.id.linearLayout);
        btnBack=findViewById(R.id.buttonBk);
        btnNext=findViewById(R.id.buttonNext);


        sliderAdapter =new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        //listeners

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage - 1);
            }
        });


    }

    public  void  addDotsIndicator(int position){
        dots=new TextView[6];
        layout.removeAllViews();
        for(int i = 0; i < dots.length; i++){

            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTrans));

            layout.addView(dots[i]);

        }
        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

 ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
     @Override
     public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

     }

     @SuppressLint("SetTextI18n")
     @Override
     public void onPageSelected(int position) {
         addDotsIndicator(position);

         mCurrentPage=position;
         if(position==0){

             btnNext.setEnabled(true);
             btnBack.setEnabled(false);
             btnBack.setVisibility(View.INVISIBLE);

             btnNext.setText("NEXT");
             btnBack.setText("");
         }else if(position==dots.length -1){

             btnNext.setEnabled(true);
             btnBack.setEnabled(true);
             btnBack.setVisibility(View.VISIBLE);

             btnNext.setText("FINISH");
             btnBack.setText("BACK");

             btnNext.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(getApplicationContext(), login.class);
                     startActivity(intent);
                 }
             });
         }else {
             btnNext.setEnabled(true);
             btnBack.setEnabled(true);
             btnBack.setVisibility(View.VISIBLE);

             btnNext.setText("NEXT");
             btnBack.setText("BACK");
         }
     }

     @Override
     public void onPageScrollStateChanged(int state) {

     }
 };
}
