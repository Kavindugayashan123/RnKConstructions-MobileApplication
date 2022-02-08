package com.example.constructor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context= context;
    }


    //Arrays

    public int[] slide_images={
      R.drawable.firstloginss,
      R.drawable.seconbg,
      R.drawable.thirdbg,
            R.drawable.fourthbg,
            R.drawable.fifthbg,
            R.drawable.sixthbg
    };

    public String[] slide_headings={
      "Welcome to RNK, Register and Login",
      "How to Insert Data !",
      "Add New Project",
            "View Main Project",
            "Salary Management",
            "Search Item"
    };

    public String[] slide_desc={
            "Enter username , password with your image to get registered.",
            "Add data under these categories & keep track of the data.",
            "Add your projects here & be organized with them.",
            "Click the any project you can view the all data in selected project.",
            "Manage your employees salaries from here to not to miss any salary details.",
            "Search for any category out of them to view their full details."
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageView=view.findViewById(R.id.imageView4);
        TextView heading=view.findViewById(R.id.textView7);
        TextView desc=view.findViewById(R.id.textView13);

        imageView.setImageResource(slide_images[position]);
        heading.setText(slide_headings[position]);
        desc.setText(slide_desc[position]);

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);

    }
}
