package com.example.constructor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.constructor.Home.myStr;

public class EmployeeSalary extends AppCompatActivity {
    ActionBar actionBar;
    Integer id;
    TextView EmpName, Type, Site, FullAmt, Amt, NetAmt, Paid, FullPaid;
    EditText OnTimeSalAmt, CurrentSite, FullSalAmount;
    Button btnAddSalary, btnSave, sendSms;
    private DataBaseHelper dbHelper;
    String TxtOnTimeAmt, Role,Name, ImageUri, Address, Phone,NIC, Gender, TimeStamp, EmpType, SiteName, Date;
    double PaidAmtCal, NetSal;
    String user;
    TextView txtUpdatedDate;
    Dialog dialog;
    Spinner projSpinner;
    String projName;
    SQLiteDatabase db;
    String projNames[];
    private  static final String DBNAME = "RnKConsn";

    private ImageView imgClose;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_salary);
        getSupportActionBar().hide();


        Window window=this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.myStatusBar));
        dbHelper = new DataBaseHelper(this);

        imgClose=findViewById(R.id.btnClose);
        EmpName = findViewById(R.id.txtName);
        Type = findViewById(R.id.txtEmpType);
        Site = findViewById(R.id.txtAssignProject);
        FullAmt = findViewById(R.id.txtFullAmt);
        Amt = findViewById(R.id.txtAmt);
        NetAmt = findViewById(R.id.txtNetAmt);
        Paid = findViewById(R.id.txtPaidAmt);
        FullPaid = findViewById(R.id.FullPaidAmt);
        btnAddSalary = findViewById(R.id.btnAddsalary);
        btnSave = findViewById(R.id.btnUpdate);
        OnTimeSalAmt = findViewById(R.id.textsalary);
        CurrentSite = findViewById(R.id.textprojectname);
        txtUpdatedDate = findViewById(R.id.emp_assignedDate);
        FullSalAmount = findViewById(R.id.txtFull);

//List down the Employees Names
        projSpinner=findViewById(R.id.spinnerProj);
        db=openOrCreateDatabase(DBNAME,SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor d=db.rawQuery("select Project_Name from MainProjects",null);
        projNames=new String[d.getCount()];


        d.moveToFirst();
        for (int i=0;i<projNames.length;i++)
        {
            projNames[i]=d.getString(0);
            d.moveToNext();
        }
        ArrayAdapter<String> adp2= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,projNames);
        projSpinner.setAdapter(adp2);





        //date picker

        txtUpdatedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(EmployeeSalary.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectDate=+dayOfMonth+ "/" +month+ "/" +year;
                txtUpdatedDate.setText(selectDate);
            }
        };


        dbHelper = new DataBaseHelper(this);
        dialog=new Dialog(this);



        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                user=myStr;
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
        });
        Intent intent = this.getIntent();
        Employee employee = intent.getParcelableExtra("Employee");

        EmpName.setText(employee.getE_NAME());
        Type.setText(employee.getE_TYPE());
        Site.setText(employee.getE_SITENAME());
        String fullamt = Double.toString(employee.getE_FULL_SALARY());
        String paidAmt = Double.toString(employee.getE_PAID_SALARY());
        FullAmt.setText(fullamt);
        FullPaid.setText(paidAmt);
        TxtOnTimeAmt = ""+OnTimeSalAmt.getText();

        FullSalAmount.setText(""+employee.getE_FULL_SALARY());


        btnAddSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TxtOnTimeAmt = ""+OnTimeSalAmt.getText();
                String added_amount = ""+Amt.getText();
                if(TxtOnTimeAmt.equals(""))
                { Toast.makeText(EmployeeSalary.this, "Please enter daily paid amount before calculate", Toast.LENGTH_SHORT).show(); }

                else if(!added_amount.equals("0000"))
                { Toast.makeText(EmployeeSalary.this, "Salary calculated Once a time", Toast.LENGTH_SHORT).show(); }

                else {
                    Double Paidvalue = Double.valueOf("" + FullPaid.getText());
                    Double OnTimeValue = Double.valueOf("" + OnTimeSalAmt.getText());
                    Double FullAmount = Double.valueOf("" + FullSalAmount.getText());
                    String fullamt = "" + FullSalAmount.getText();
                    FullAmt.setText(fullamt);
                    PaidAmtCal = (Paidvalue + OnTimeValue);
                    NetSal = (FullAmount - PaidAmtCal);
                    if (PaidAmtCal < FullAmount) {
                        Toast.makeText(EmployeeSalary.this, "Successfully updated salary amount", Toast.LENGTH_SHORT).show();
                    } else if (PaidAmtCal > FullAmount) {
                        Toast.makeText(EmployeeSalary.this, "Can't add the payment. Out range of Full Salary amount", Toast.LENGTH_SHORT).show();
                    }

                    Paid.setText(Paidvalue.toString());
                    Amt.setText(OnTimeValue.toString());
                    String txtCalPaidAmt = ("(" + PaidAmtCal + ")");
                    String txtNetSal = "" + NetSal;
                    FullPaid.setText(txtCalPaidAmt);
                    NetAmt.setText(txtNetSal);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double OnTimeValue = Double.valueOf(""+OnTimeSalAmt.getText());
                Double FullAmount = Double.valueOf(""+FullAmt.getText());
                Float FOnTime, FfullAmt, FPaidAmt;

                id = employee.getE_ID();
                EmpType = employee.getE_ROLE();
                Role = employee.getE_ROLE();
                Name = employee.getE_NAME();
                ImageUri = employee.getE_IMAGE();
                Address = employee.getE_ADDRESS();
                Phone = employee.getE_PHONE();
                NIC = employee.getE_NIC();
                Gender = employee.getE_GENDER();
                TimeStamp = ""+System.currentTimeMillis();
                SiteName = ""+projSpinner.getSelectedItem().toString().trim();
                Date = ""+txtUpdatedDate.getText().toString().trim();

                FOnTime  = Float.valueOf(String.valueOf(OnTimeValue)) ;
                FfullAmt  = Float.valueOf(String.valueOf(FullAmount)) ;
                FPaidAmt  = Float.valueOf(String.valueOf(PaidAmtCal)) ;
                Float NetAmt = (FfullAmt - FPaidAmt);

                if(PaidAmtCal <= FfullAmt)
                {
                Employee employee = new Employee(id, EmpType, Role, Name, ImageUri, Address, Phone,NIC, Gender, TimeStamp, TimeStamp, SiteName, Date, FOnTime, FfullAmt, FPaidAmt);
                int State = dbHelper.updateEmployeeInfo(employee);
                if(State == 1)
                    Toast.makeText(EmployeeSalary.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EmployeeSalary.this, "Updated Failed, Please try again", Toast.LENGTH_SHORT).show();
                    user=myStr;
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                }
                else
                { Toast.makeText(EmployeeSalary.this, "Updated Failed, Out range of Full Salary amount", Toast.LENGTH_SHORT).show();}
            }
        });



    }
}