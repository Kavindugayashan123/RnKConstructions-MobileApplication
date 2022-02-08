package com.example.constructor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import static com.example.constructor.Home.myStr;

public class SearchItemDetail extends AppCompatActivity {

    LinearLayout layout_assign_project,layout_address,telephone_layout,layout_gender;
    LinearLayout layout_name,layout_category;
    LinearLayout layout_age,layout_nic,layout_employee_type;
    RelativeLayout relativeLayout;
    private DataBaseHelper dbHelper;
    EditText searchText;
    ImageView img_search;
    String strname, ImageUri;
    Integer ID;
    Button btnEdit, btnDelete;
    Context context;
    EditText txt_name ;
    EditText txt_category ;
    EditText txt_employee ;
    EditText txt_nic_name ;
    EditText txt_age ;
    EditText txt_assign_project;
    EditText txt_address;
    EditText txt_telephone;
    EditText txt_gender;
    Cursor cursor;
    ImageView img_employee , imgClose;
    RadioButton radio_employee,radio_customer,
            radio_vehicle,radio_stock,radio_machine;

    TextView lbl_name,lbl_category,
            lbl_employee_type,lbl_nic,lbl_age,
            lbl_assign_project,lbl_address,lbl_phone;

    String user;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_detail_item);
        getSupportActionBar().hide();

        Window window=this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.myStatusBar));

        dbHelper = new DataBaseHelper(this);

        //layout
        layout_assign_project = findViewById(R.id.layout_assign_project);
        layout_address = findViewById(R.id.layout_address);
        telephone_layout = findViewById(R.id.telephone_layout);
        layout_gender = findViewById(R.id.gender_layout);
        layout_name = findViewById(R.id.layout_name);
        layout_category = findViewById(R.id.layout_category);
        layout_employee_type = findViewById(R.id.layout_employee_type);
        //

        txt_gender= findViewById(R.id.txt_gender);
        layout_age = findViewById(R.id.layout_age);
        layout_nic= findViewById(R.id.layout_nic);

        img_employee = findViewById(R.id.img_employee);

        txt_name = findViewById(R.id.txt_name);
        txt_category = findViewById(R.id.txt_category);
        txt_employee = findViewById(R.id.txt_employee);
        txt_nic_name = findViewById(R.id.txt_nic_name);
        txt_age = findViewById(R.id.txt_age);
        txt_assign_project = findViewById(R.id.txt_assign_project);
        txt_address = findViewById(R.id.txt_address);
        txt_telephone = findViewById(R.id.txt_telephone);
        imgClose=findViewById(R.id.btnClose);
        btnEdit = findViewById(R.id.btnEdit);

        lbl_name= findViewById(R.id.lbl_name);
        lbl_category= findViewById(R.id.lbl_category);
        lbl_employee_type= findViewById(R.id.lbl_employee_type);
        lbl_nic= findViewById(R.id.lbl_nic);
        lbl_age= findViewById(R.id.lbl_age);
        lbl_assign_project= findViewById(R.id.lbl_assign_project);
        lbl_address= findViewById(R.id.lbl_address);
        lbl_phone= findViewById(R.id.lbl_phone);

        btnDelete=findViewById(R.id.btnDelete2);
        context=this;

        Intent intent = this.getIntent();
        Employee employee = intent.getParcelableExtra("Employee");
        Customer customer = intent.getParcelableExtra("Customer");
        Vehicle vehicle = intent.getParcelableExtra("Vehicle");
        Machine machine = intent.getParcelableExtra("Machine");
        Stoke stock = intent.getParcelableExtra("Stock");

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


        if(intent.hasExtra("Employee"))
        {
            layout_name.setVisibility(View.VISIBLE);
            layout_assign_project.setVisibility(View.VISIBLE);
            layout_address.setVisibility(View.VISIBLE);
            telephone_layout.setVisibility(View.VISIBLE);
            layout_gender.setVisibility(View.VISIBLE);
            layout_category.setVisibility(View.VISIBLE);
            layout_employee_type.setVisibility(View.VISIBLE);
            layout_nic.setVisibility(View.VISIBLE);
            layout_age.setVisibility(View.VISIBLE);

            lbl_name.setText("Name : ");
            lbl_category.setText("Address :");
            lbl_employee_type.setText("Telephone :");
            lbl_nic.setText("NIC : ");

            txt_name.setText(employee.getE_NAME());
            img_employee.setImageURI(Uri.parse(employee.getE_IMAGE()));
            txt_category.setText(employee.getE_ADDRESS());
            txt_employee.setText(employee.getE_PHONE());
            txt_age.setText(employee.getE_NIC());
            //txt_assign_project.setText(employee.getE_NIC());
            txt_address.setText(employee.getE_ROLE());
            txt_telephone.setText(employee.getE_TYPE());
            txt_nic_name.setText(employee.getE_NIC());
            txt_gender.setText(employee.getE_GENDER());
            ID = employee.getE_ID();
            ImageUri = employee.getE_IMAGE();

            layout_age.setVisibility(View.GONE);
            layout_assign_project.setVisibility(View.GONE);


            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Name, Address, Phone, NIC, Role, Employee, Type, Gender,TimeStamp,Image, SiteName, Date;
                    Float Salary, FullSalary, PaidSalary;
                    Type = ""+txt_telephone.getText().toString().trim();
                    Role = ""+txt_address.getText().toString().trim();
                    Name = ""+txt_name.getText().toString().trim();
                    Address = ""+txt_category.getText().toString().trim();
                    TimeStamp = ""+System.currentTimeMillis();
                    Phone = ""+txt_employee.getText().toString().trim();
                    NIC = ""+txt_nic_name.getText().toString().trim();
                    Gender = ""+txt_gender.getText().toString().trim();
                    Image = ""+img_employee.toString().trim();
                    SiteName = employee.getE_SITENAME();
                    Date = ""+System.currentTimeMillis();
                    Salary = employee.getE_SALARY();
                    FullSalary = employee.getE_FULL_SALARY();
                    PaidSalary = employee.getE_PAID_SALARY();

                    Employee employee = new Employee(ID, Type, Role, Name, ImageUri, Address, Phone, NIC, Gender, TimeStamp, TimeStamp, SiteName, Date, Salary, FullSalary, PaidSalary);
                    int State = dbHelper.updateEmployeeInfo(employee);
                    if(State == 1)
                    Toast.makeText(SearchItemDetail.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                    Toast.makeText(SearchItemDetail.this, "Updated Failed, Please try again", Toast.LENGTH_SHORT).show();
                    user=myStr;
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
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

                            dbHelper.deleteEmployee(ID);
                            user=myStr;
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }
            });

        }

        if(intent.hasExtra("Customer"))
        {
            layout_name.setVisibility(View.VISIBLE);
            layout_category.setVisibility(View.VISIBLE);
            layout_employee_type.setVisibility(View.VISIBLE);
            layout_nic.setVisibility(View.VISIBLE);
            layout_age.setVisibility(View.VISIBLE);

            lbl_name.setText("Name : ");
            lbl_category.setText("Address :");
            lbl_employee_type.setText("Telephone :");
            lbl_nic.setText("NIC : ");
            lbl_age.setText("Assign Date : ");
            String stockName = customer.getC_NAM().toString() ;
            txt_name.setText(stockName + "");
           // txt_category.setText(customer.get);
            txt_employee.setText(customer.getC_PHONE());
            img_employee.setImageURI(Uri.parse(customer.getC_IMAGE()));
            txt_category.setText(customer.getC_ADDRESS());
            txt_nic_name.setText(customer.getC_NIC());
            txt_age.setText(customer.getC_DATE());
            txt_assign_project.setText(customer.getC_UPDATE_TIMESTAMP());
            //txt_assign_project.setText(stxt_assign_project+"");
            //txt_address.setText(customer.getC_NIC());
            //txt_telephone.setText(customer.getC_DATE());
            ID = customer.getC_ID();
            ImageUri = customer.getC_IMAGE();

            layout_assign_project.setVisibility(View.GONE);
            layout_address.setVisibility(View.GONE);
            telephone_layout.setVisibility(View.GONE);
            layout_gender.setVisibility(View.GONE);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Name, Address, Phone, NIC, Age, Employee, Type, Gender,TimeStamp,Date;
                    Name = ""+txt_name.getText().toString().trim();
                    Address = ""+txt_category.getText().toString().trim();
                    TimeStamp = ""+System.currentTimeMillis();
                    Phone = ""+txt_employee.getText().toString().trim();
                    NIC = ""+txt_nic_name.getText().toString().trim();
                    Date = ""+txt_age.getText().toString().trim();


                    Customer customer = new Customer(ID, Name, Address, Phone, ImageUri, TimeStamp, TimeStamp, NIC, Date );
                    int State = dbHelper.updateCustomerInfo(customer);
                    if(State == 1)
                        Toast.makeText(SearchItemDetail.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SearchItemDetail.this, "Updated Failed, Please try again", Toast.LENGTH_SHORT).show();
                    user=myStr;
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
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
                            dbHelper.deleteCustomer(ID);
                            user=myStr;
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }
            });

        }

        if(intent.hasExtra("Vehicle"))
        {
            layout_name.setVisibility(View.VISIBLE);
            layout_category.setVisibility(View.VISIBLE);
            layout_employee_type.setVisibility(View.VISIBLE);

            lbl_name.setText("Vehicle No : ");
            lbl_category.setText("Vehicle Name :");
            lbl_employee_type.setText("Vehicle type :");

            String stockName = vehicle.getV_NU();
            txt_name.setText(stockName + "");
            txt_category.setText(vehicle.getV_NAME());
            //txt_name.setText(cursor.getString(3)+"");
            txt_employee.setText(vehicle.getV_TYPE());
            img_employee.setImageURI(Uri.parse(vehicle.getV_IMAGE()));
            ID = vehicle.getV_ID();
            ImageUri = vehicle.getV_IMAGE();

            layout_assign_project.setVisibility(View.GONE);
            layout_address.setVisibility(View.GONE);
            telephone_layout.setVisibility(View.GONE);
            layout_age.setVisibility(View.GONE);
            layout_nic.setVisibility(View.GONE);
            layout_gender.setVisibility(View.GONE);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Name, Number, Type,TimeStamp;
                    Name = ""+txt_category.getText().toString().trim();
                    Number = ""+
                            txt_name.getText().toString().trim();
                    TimeStamp = ""+System.currentTimeMillis();
                    Type = ""+txt_employee.getText().toString().trim();


                    Vehicle vehicle = new Vehicle(ID, Number, Name, Type, ImageUri, TimeStamp, TimeStamp);

                     
                    int State = dbHelper.updateVehicleInfo(vehicle);
                    if(State == 1)
                        Toast.makeText(SearchItemDetail.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SearchItemDetail.this, "Updated Failed, Please try again", Toast.LENGTH_SHORT).show();
                    user=myStr;
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
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
                            dbHelper.deleteVehicle(ID);
                            user=myStr;
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }
            });

        }

        if(intent.hasExtra("Machine"))
        {
            lbl_name.setText("Machine Number : ");
            lbl_category.setText("Machine Name :");



            txt_name.setText(machine.getM_NUM());
            txt_category.setText(machine.getM_NAME());
            img_employee.setImageURI(Uri.parse(machine.getM_IMAGE()));
            //txt_name.setText(cursor.getString(3)+"");


            layout_employee_type.setVisibility(View.GONE);
            layout_assign_project.setVisibility(View.GONE);
            layout_address.setVisibility(View.GONE);
            telephone_layout.setVisibility(View.GONE);
            layout_age.setVisibility(View.GONE);
            layout_nic.setVisibility(View.GONE);
            layout_gender.setVisibility(View.GONE);
            ID = machine.getM_ID();
            ImageUri = machine.getM_IMAGE();

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Name, Number, Type,TimeStamp, Site_ID;
                    Name = ""+txt_category.getText().toString().trim();
                    Number = ""+txt_name.getText().toString().trim();
                    TimeStamp = ""+System.currentTimeMillis();
                    Site_ID = "";


                    Machine machine = new Machine(ID, Number, Name, ImageUri, TimeStamp, TimeStamp, Site_ID);
                    int State = dbHelper.updateMachineInfo(machine);
                    if(State == 1)
                        Toast.makeText(SearchItemDetail.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SearchItemDetail.this, "Updated Failed, Please try again", Toast.LENGTH_SHORT).show();
                    user=myStr;
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
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
                            dbHelper.deleteMachine(ID);
                            user=myStr;
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }
            });

        }

        if(intent.hasExtra("Stock"))
        {
            layout_name.setVisibility(View.VISIBLE);
            layout_category.setVisibility(View.VISIBLE);
            layout_employee_type.setVisibility(View.VISIBLE);
            layout_nic.setVisibility(View.VISIBLE);
            layout_age.setVisibility(View.VISIBLE);

            lbl_name.setText("Stock Name : ");
            lbl_category.setText("Quantity :");
            lbl_employee_type.setText("Date :");
            lbl_nic.setText("Cost : ");
            lbl_age.setText("Site Name : ");
            String stockName = stock.getS_NAME();
            txt_name.setText(stockName + "");
            txt_category.setText(stock.getS_QTY());
            img_employee.setImageURI(Uri.parse(stock.getS_IMAGE()));
            //  txt_name.setText(cursor.getString(3)+"");
            txt_employee.setText(stock.getS_DATE());
            txt_nic_name.setText(stock.getS_SALARY());
            txt_age.setText(stock.getS_SITENAME());
            ID = stock.getS_ID();
            ImageUri = stock.getS_IMAGE();


            layout_assign_project.setVisibility(View.GONE);
            layout_address.setVisibility(View.GONE);
            telephone_layout.setVisibility(View.GONE);
            layout_gender.setVisibility(View.GONE);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Name, Number, Type,TimeStamp, SiteName, Salary, Date, Qty;
                    Name = ""+txt_name.getText().toString().trim();
                    TimeStamp = ""+System.currentTimeMillis();
                    Date = ""+txt_employee.getText().toString().trim();
                    Qty = ""+txt_category.getText().toString().trim();
                    SiteName =""+txt_age.getText().toString().trim();
                    Salary = ""+txt_nic_name.getText().toString().trim();


                    Stoke stock = new Stoke(ID, Name, Qty, ImageUri, TimeStamp, TimeStamp, Date, Salary,SiteName);
                    int State = dbHelper.updateStockInfo(stock);
                    if(State == 1)
                        Toast.makeText(SearchItemDetail.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SearchItemDetail.this, "Updated Failed, Please try again", Toast.LENGTH_SHORT).show();
                    user=myStr;
                    Intent intent = new Intent(getApplicationContext(), SearchItemDetail.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
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

                            dbHelper.deleteStock(ID);
                            user=myStr;
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }
            });

        }

    }
}
