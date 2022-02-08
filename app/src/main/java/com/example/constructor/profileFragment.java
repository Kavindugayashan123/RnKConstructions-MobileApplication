package com.example.constructor;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.constructor.Home.myStr;

public class profileFragment extends Fragment {




    private DataBaseHelper dbHelper;
    ImageView imageView;
    Button Logout;
    TextView post;
    TextView uname;
    Cursor cursor;
    String txt;
    TextView ecount;
    Dialog dialog;
    TextView mProects,eCounts,sCounts,vCounts,cCounts,mCounts,pCounts,ongoingCount,finishedProj;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Logout = v.findViewById(R.id.btnlogout);
        imageView = v.findViewById(R.id.image);
        post = v.findViewById(R.id.textView19);
        uname = v.findViewById(R.id.textView18);
        finishedProj = v.findViewById(R.id.eCount);
        ongoingCount=v.findViewById(R.id.ongoingP);

        mProects=v.findViewById(R.id.pCountMain);

        eCounts=v.findViewById(R.id.pLocation);
        sCounts=v.findViewById(R.id.txtStokes);
        vCounts=v.findViewById(R.id.txtCusName);
        cCounts=v.findViewById(R.id.txtOthers);
        mCounts=v.findViewById(R.id.eTimes);
        pCounts=v.findViewById(R.id.pName);


        //login Loading
        dialog=new Dialog(getContext());

        txt = myStr;
        uname.setText(myStr);
        txt = "'" + txt + "'";

        dbHelper = new DataBaseHelper(getActivity());
        String POSTS_SELECT_QUERY =
                String.format("Select * from Register  WHERE Rname = " + txt);

        SQLiteDatabase db = dbHelper.getReadableDatabase();


        int numRows = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM MainProjects where Finished !=0", null);
        String count = String.valueOf(numRows);
        finishedProj.setText(count);


        int numRowsEmployee = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM Employeee", null);
        String countEmployees = String.valueOf(numRowsEmployee);
        eCounts.setText(countEmployees);



        int numRowsCustomers = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM Customer", null);
        String countCustomers = String.valueOf(numRowsCustomers);
        cCounts.setText(countCustomers);

        int numRowsMainProjects = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM MainProjects", null);
        String countProjects = String.valueOf(numRowsMainProjects);
        mProects.setText(countProjects);
        pCounts.setText(countProjects);

        int numRowsStocks = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM Stoke", null);
        String countStocks = String.valueOf(numRowsStocks);
        sCounts.setText(countStocks);

        int numRowsVehicles = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM Vehicle", null);
        String countVehicles = String.valueOf(numRowsVehicles);
        vCounts.setText(countVehicles);

        int numRowsMachines = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM Machine", null);
        String countMachines = String.valueOf(numRowsMachines);
        mCounts.setText(countMachines);


        int ongoing = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM MainProjects where Finished=0", null);
        String countOngoingProjects = String.valueOf(ongoing);
        ongoingCount.setText(countOngoingProjects);



        try {
            cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

            if (cursor.moveToFirst()) {
                do {

                    imageView.setImageURI(Uri.parse(cursor.getString(3)));


                } while (cursor.moveToNext());
            }

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

            Logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    openDilogLoding();
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            closeDilogLoding();
                            Intent intent = new Intent(profileFragment.this.getContext(), login.class);
                            startActivity(intent);
                        }
                    },4000);

                }
            });


            return v;
        }


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

