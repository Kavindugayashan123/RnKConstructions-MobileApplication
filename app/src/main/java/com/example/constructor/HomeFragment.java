package com.example.constructor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.canvas.CanvasCompat;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.constructor.Home.myStr;

public class HomeFragment extends Fragment {
    private DataBaseHelper dbHelper;
    Button btnAddNewProject;
    Window window;
    Cursor cursor;
     String uname;
    TextView greeting;
    ImageView imageView;
    private ListView listView;
    private List<MainProject> mainProjects;
    Context context;





    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home,container,false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        Window window=((Activity)view.getContext()).getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.myStatusBar));

        btnAddNewProject=view.findViewById(R.id.btnAddNewProject);
        imageView=view.findViewById(R.id.imageViewHOME);
        greeting=view.findViewById(R.id.textuser);


        listView=view.findViewById(R.id.listView);


        //load list
        context=view.getContext();
        dbHelper=new DataBaseHelper(context);
        mainProjects=new ArrayList<>();
        mainProjects=dbHelper.getAllProjects();


        projectsAdaptor adapter=new projectsAdaptor(context,R.layout.row,mainProjects);
        listView.setAdapter(adapter);


        uname=myStr;
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            greeting.setText("Good Morning "+myStr );
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greeting.setText("Good Afternoon "+myStr);
        }else if(timeOfDay >= 16 && timeOfDay < 24){
            greeting.setText("Good Evening "+myStr);
        }

        uname="'"+uname+"'";

        dbHelper = new DataBaseHelper(getActivity());

        String POSTS_SELECT_QUERY =
                String.format("Select * from Register  WHERE Rname = " + uname);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

            if (cursor.moveToFirst()) {
                do {

                    imageView.setImageURI(Uri.parse(cursor.getString(3)));

                } while(cursor.moveToNext());
            }

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        btnAddNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeFragment.this.getContext(),addMainProject.class);
                startActivity(intent);
            }
        });




        //click list Items
        listView.setOnItemClickListener(new  AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final  MainProject mainProject=mainProjects.get(position);

                Intent intent=new Intent(getContext(),Project_View.class);
                intent.putExtra("pId",mainProject.getId());
                intent.putExtra("pName",mainProject.getProjectName());
                intent.putExtra("location",mainProject.getLocation());
                intent.putExtra("others",mainProject.getOthers());
                intent.putExtra("cusName",mainProject.getCustomerName());
                intent.putExtra("estTime",mainProject.geteTime());
                intent.putExtra("estCost",mainProject.geteCost());
                intent.putExtra("empNames",mainProject.getEmployees());
                intent.putExtra("stokesNames",mainProject.getStokes());
                intent.putExtra("machinesNames",mainProject.getMachines());
                intent.putExtra("finished",mainProject.getFinished());

                startActivity(intent);


            }
        });

        return view;


    }

}

