package com.example.constructor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.ArrayList;

import static com.example.constructor.Home.myStr;

public class addMainProject extends AppCompatActivity implements View.OnClickListener {

    private  ImageView btnClose;
    private  Button   addMainProject;
    Spinner cusSpinner,empSpinner ,stockSpinner,machineSpinner;
    SQLiteDatabase db;
    String cusName[];
    String empName[];
    String stockName[];
    String machineName[];
    ListView projectList;
    EditText projectName,location,otherInformation,estimatedTime,estCost,finished;
    String EmployeeNames=null;
    ArrayList<String> empNames=new ArrayList<>();
    ArrayList<String> stockNames=new ArrayList<>();
    ArrayList<String> MachineNames=new ArrayList<>();

    LinearLayout layoutList,layoutMachines,layoutStock;
    Button btnAddEmployee, btnAddMachines, btnAddStokes;
    private DataBaseHelper dataBaseHelper;
    private Context context;
    Dialog dialog;

    AwesomeValidation awesomeValidation;
    private  static final String DBNAME = "RnKConsn";

    String user;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main_project);
        getSupportActionBar().hide();

        Window window=this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.myStatusBar));


        btnClose=findViewById(R.id.btnClose);
        addMainProject=findViewById(R.id.addMainProject);
        projectList=findViewById(R.id.listView);
        projectName=findViewById(R.id.textprojectname);
        location=findViewById(R.id.textprojectlocation);
        otherInformation=findViewById(R.id.textprojectotherinformation);
        estimatedTime=findViewById(R.id.textprojectTime);
        estCost=findViewById(R.id.textprojectCost);

        context=this;
        dataBaseHelper=new DataBaseHelper(context);
        dialog=new Dialog(this);

        addMainProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                     if(empNames.isEmpty() || stockNames.isEmpty() || MachineNames.isEmpty()) {
                         Toast.makeText(context," Please add Data First ! ",Toast.LENGTH_SHORT).show();

                    }else {
                         openDilogSuccessfull();
                         Handler handler=new Handler();
                         handler.postDelayed(new Runnable() {
                             @Override
                             public void run() {

                                 closeDilogSuccessfull();
                                 long Started=System.currentTimeMillis();
                                 String customerSpinnerVal=cusSpinner.getSelectedItem().toString();
                                 String ProjectName=projectName.getText().toString();
                                 String Location=location.getText().toString();
                                 String OtherData=otherInformation.getText().toString();
                                 String EstTime=estimatedTime.getText().toString();
                                 String EstCost=estCost.getText().toString();
                                 String employeeSpinnerVal=empNames.toString();
                                 String stockSpinnerVal=stockNames.toString();
                                 String machineSpinnerVal=MachineNames.toString();


                                 MainProject mainProject=new MainProject(Started,ProjectName,customerSpinnerVal,Location,OtherData,employeeSpinnerVal,stockSpinnerVal,machineSpinnerVal,EstTime,EstCost,0);
                                 dataBaseHelper.addMainProject(mainProject);


                                 user=myStr;
                                 Intent intent = new Intent(getApplicationContext(), Home.class);
                                 intent.putExtra("username", user);
                                 startActivity(intent);


                             }
                         },1500);

                     }



                }
            }
        });




        //Add as List all DB data
        layoutList=findViewById(R.id.tableLayout);
        layoutMachines=findViewById(R.id.tableLayout2);
        layoutStock=findViewById(R.id.tableLayout3);

        btnAddEmployee=findViewById(R.id.addEmployee);
        btnAddMachines=findViewById(R.id.addStoke);
        btnAddStokes=findViewById(R.id.addMachine);

        btnAddEmployee.setOnClickListener(this);

        btnAddMachines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addViewMachines();
            }
        });

        btnAddStokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addViewStokes();
            }
        });

        //List down the Customer Names
        cusSpinner=findViewById(R.id.spinner);
        db=openOrCreateDatabase(DBNAME,SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor c=db.rawQuery("select NAME from Customer",null);
        cusName=new String[c.getCount()];

        c.moveToFirst();
        for (int i=0;i<cusName.length;i++)
        {
            cusName[i]=c.getString(0);
            c.moveToNext();
        }
        ArrayAdapter<String> adp= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cusName);
        cusSpinner.setAdapter(adp);

        //List down the Employees Names
        empSpinner=findViewById(R.id.projectemplyeedetails);
        Cursor d=db.rawQuery("select ENAME from Employeee",null);
        empName=new String[d.getCount()];


        d.moveToFirst();
        for (int i=0;i<empName.length;i++)
        {
            empName[i]=d.getString(0);
            d.moveToNext();
        }
        ArrayAdapter<String> adp2= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,empName);
        empSpinner.setAdapter(adp2);

        //List down the Stocks Names
        stockSpinner=findViewById(R.id.projectmachinedetails);
        Cursor e=db.rawQuery("select SNAME from Stoke",null);
        stockName=new String[e.getCount()];

        e.moveToFirst();
        for (int i=0;i<stockName.length;i++)
        {
            stockName[i]=e.getString(0);
            e.moveToNext();
        }
        ArrayAdapter<String> adp3= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,stockName);
        stockSpinner.setAdapter(adp3);

        //List down the Machines Names
        machineSpinner=findViewById(R.id.projectstokedetails);
        Cursor f=db.rawQuery("select MNAME from Machine",null);
        machineName=new String[f.getCount()];

        f.moveToFirst();
        for (int i=0;i<machineName.length;i++)
        {
            machineName[i]=f.getString(0);
            f.moveToNext();
        }
        ArrayAdapter<String> adp4= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,machineName);
        machineSpinner.setAdapter(adp4);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.textprojectname, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.textprojectlocation, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.textprojectotherinformation, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.projectemplyeedetails, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.projectstokedetails, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.projectmachinedetails, RegexTemplate.NOT_EMPTY,R.string.not_null);


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    @Override
    public void onClick(View v) {

        addView();
    }

    private void addView(){

        View crView=getLayoutInflater().inflate(R.layout.row_new_employee,null,false);

        TextView txtName=crView.findViewById(R.id.txtName);

        String state=empSpinner.getSelectedItem().toString();
        empNames.add(state);
        txtName.setText(state);
        ImageView imageView=crView.findViewById(R.id.clsBtn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeView(crView);
            }
        });


        layoutList.addView(crView);
    }
    private void removeView(View view){

        layoutList.removeView(view);
    }

    //Machines
    private void addViewMachines(){

        View crViewMachines=getLayoutInflater().inflate(R.layout.row_new_employee,null,false);

        TextView txtName=crViewMachines.findViewById(R.id.txtName);

        String state=machineSpinner.getSelectedItem().toString();
        MachineNames.add(state);
        txtName.setText(state);
        ImageView imageView=crViewMachines.findViewById(R.id.clsBtn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeViewMachines(crViewMachines);
            }
        });


        layoutMachines.addView(crViewMachines);
    }
    private void removeViewMachines(View view){

        layoutMachines.removeView(view);
    }

    //Stokes
    private void addViewStokes(){

        View crViewStokes=getLayoutInflater().inflate(R.layout.row_new_stokes,null,false);

        TextView txtName=crViewStokes.findViewById(R.id.txtName);
        EditText txtQty=crViewStokes.findViewById(R.id.txtQty);;

        String state=stockSpinner.getSelectedItem().toString();
        stockNames.add(state);
        txtName.setText(state);
        ImageView imageView=crViewStokes.findViewById(R.id.clsBtn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeViewStokes(crViewStokes);
            }
        });


        layoutStock.addView(crViewStokes);
    }
    private void removeViewStokes(View view){

        layoutStock.removeView(view);
    }

    private void openDilogSuccessfull(){
        dialog.setContentView(R.layout.dilog_succsess);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }
    private void closeDilogSuccessfull(){
        dialog.dismiss();
    }
}
