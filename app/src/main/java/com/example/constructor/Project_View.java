package com.example.constructor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

import static com.example.constructor.Home.myStr;

public class Project_View extends AppCompatActivity {

    TextView projName,projLocation,others,cusName,cusMobile,eTime,eCost,stDate;
    Button btnDelete, btnUpdate,btnFinished;
    Context context;
    private DataBaseHelper dbHelper;
    private List<Customer> customer;
    ListView ENamesList,StocksList,MachinesList;
    ImageView imgClose;
    String user;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project__view);
        getSupportActionBar().hide();

        Window window=this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.myStatusBar));
        imgClose=findViewById(R.id.btnClose);

        context=this;
        btnDelete=findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnvehicle);
        btnFinished=findViewById(R.id.btnFinished);
        projName=findViewById(R.id.pName);
        projLocation=findViewById(R.id.pLocation);
        others=findViewById(R.id.txtOthers);
        cusName=findViewById(R.id.txtCusName);
        cusMobile=findViewById(R.id.txtCusMobile);
        eTime=findViewById(R.id.eTimes);
        eCost=findViewById(R.id.eCosts);
        stDate=findViewById(R.id.stDaate);


        dbHelper=new DataBaseHelper(context);




        String P_Name=getIntent().getStringExtra("pName");
        String P_Location=getIntent().getStringExtra("location");
        String P_Others=getIntent().getStringExtra("others");
        int Id=getIntent().getIntExtra("pId",0);
        String Customer_name=getIntent().getStringExtra("cusName");;
        String Est_Time=getIntent().getStringExtra("estTime");;
        String Est_Cost=getIntent().getStringExtra("estCost");;
        String Emp_Names=getIntent().getStringExtra("empNames");;
        String Stokes_Names=getIntent().getStringExtra("stokesNames");;
        String Machines_Names=getIntent().getStringExtra("machinesNames");;



        String removeFChraracter=removeFirst(Emp_Names);
        String removestockFChraracter=removeFirst(Stokes_Names);
        String removeMachinesFChraracter=removeFirst(Machines_Names);
        String[] splitNames=removeFChraracter.split(",");
        String[] splitStokesNames=removestockFChraracter.split(",");
        String[] splitMachinesNames=removeMachinesFChraracter.split(",");


        //Toast.makeText(context,splitNames[0]+" "+splitNames[1]+" "+splitNames[2],Toast.LENGTH_SHORT).show();

        projName.setText(P_Name);
        projLocation.setText(P_Location);
        others.setText(P_Others);
        cusName.setText(Customer_name);
        eTime.setText(Est_Time);
        eCost.setText(Est_Cost);



        ENamesList=findViewById(R.id.tableLayout);
        StocksList=findViewById(R.id.tableLayout1);
        MachinesList=findViewById(R.id.tableLayout2);



        ArrayAdapter<String> contactAdp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,splitNames);
        ENamesList.setAdapter(contactAdp);
        ArrayAdapter<String> contactAdp1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,splitStokesNames);
        StocksList.setAdapter(contactAdp1);
        ArrayAdapter<String> contactAdp2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,splitMachinesNames);
        MachinesList.setAdapter(contactAdp2);




        //set Customer details
        customer=new ArrayList<>();
        customer=dbHelper.getAllCustomers(Customer_name);
        Customer cus = customer.get(0);


        cusMobile.setText(cus.getC_PHONE());
        stDate.setText(cus.getC_DATE());



        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Is this project finished ?" );



                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainProject mainProject=dbHelper.getSingleProject(Id);
                        mainProject.setFinished(System.currentTimeMillis());
                        dbHelper.updateSingleData(mainProject);
                        user=myStr;
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        intent.putExtra("username", user);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Are you sure want to Delete ?" );



                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dbHelper.deleteProject(Id);
                        user=myStr;
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        intent.putExtra("username", user);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  finish();
                user=myStr;
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"id ="+Id,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),editMainProject.class);
                intent.putExtra("Pid",Id);
                startActivity(intent);


            }
        });
    }



    public String removeFirst(String namesBysplit){

        namesBysplit=namesBysplit.substring(1,namesBysplit.length()-1);
        return namesBysplit;
    }
}
