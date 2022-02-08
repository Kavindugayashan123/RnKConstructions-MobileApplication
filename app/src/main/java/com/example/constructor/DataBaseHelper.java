package com.example.constructor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.util.ArrayList;
import java.util.List;

public class  DataBaseHelper extends SQLiteOpenHelper {

    private  static final int VERSION = 6;
    private  static final String DBNAME = "RnKConsn";
    private  static final String TABLE_Customer = "Customer";
    private static final String TABLE_Employeee = "Employeee";
    private  static final String TABLE_Vehicle = "Vehicle";
    private static final String TABLE_Stoke = "Stoke";
    private  static final String TABLE_Machine = "Machine";
    private static final String TABLE_Register = "Register";
    private static final String TABLE_Main_Project = "MainProjects";

    private Context context;

    //Main Project
    public static final String PROJECT_ID= "Id";
    public static final String START_Time= "Started_Time";
    public static final String PROJECT_NAME= "Project_Name";
    public static final String CUS_NAME= "Customer_Name";
    public static final String LOCATION= "Location";
    public static final String OTHER_INFO= "Other_Data";
    public static final String EMPLOYEES= "Employees";
    public static final String STOKES= "Stokes";
    public static final String MACHINES= "Machines";
    public static final String ESTIMATED_TIME= "Estimated_Time";
    public static final String ESTIMATED_COST= "Estimated_Cost";
    public static final String FINISHED= "Finished";

   //custom
    public static final String C_ID= "ID";
    public static final String C_NAME= "NAME";
    public static final String C_ADDRESS= "ADDRESS";
    public static final String C_PHONE= "PHONE";
    public static final String C_IMAGE= "IMAGE";
    public static final String C_ADD_TIMESTAMP= "ADD_TIMESTAMP";
    public static final String C_UPDATE_TIMESTAMP= "UPDATE_TIMESTAMP";
    public static final String C_NIC= "NIC";
    public static final String C_DATE= "DATE";


   //employeee
    public static final String E_ID= "EID";
    public static final String E_TYPE= "ETYPE";
    public static final String E_ROLE= "EROLE";
    public static final String E_NAME= "ENAME";
    public static final String E_IMAGE= "EIMAGE";
    public static final String E_ADD_TIMESTAMP= "EADD_TIMESTAMP";
    public static final String E_UPDATE_TIMESTAMP= "EUPDATE_TIMESTAMP";
    public static final String E_ADDRESS= "EADDRESS";
    public static final String E_PHONE= "EPHONE";
    public static final String E_NIC= "ENIC";
    public static final String E_GENDER= "EGENDER";
    public static final String E_SITENAME= "ESITENAME";
    public static final String E_SALARY= "ESALARY";
    public static final String E_FULL_SALARY= "EFULLSALARY";
    public static final String E_PAID_SALARY= "EPAIDSALARY";
    public static final String E_DATE= "EDATE";


   //vehicle
   public static final String V_ID= "VID";
    public static final String V_NU= "VNU";
    public static final String V_NAME= "VNAME";
    public static final String V_TYPE= "VTYPE";
    public static final String V_IMAGE= "VIMAGE";
    public static final String V_ADD_TIMESTAMP= "VADD_TIMESTAMP";
    public static final String V_UPDATE_TIMESTAMP= "VUPDATE_TIMESTAMP";



  //stoke
    public static final String S_ID= "SID";
    public static final String S_NAME= "SNAME";
    public static final String S_QTY= "SQTY";
    public static final String S_IMAGE= "SIMAGE";
    public static final String S_ADD_TIMESTAMP= "SADD_TIMESTAMP";
    public static final String S_UPDATE_TIMESTAMP= "SUPDATE_TIMESTAMP";
    public static final String S_DATE= "SDATE";
    public static final String S_SALARY= "SSALARY";
    public static final String S_SITENAME= "SSITENAME";



 // machine
    public static final String M_ID= "MID";
    public static final String M_NUM= "MNUM";
    public static final String M_NAME= "MNAME";
    public static final String M_IMAGE= "MIMAGE";
    public static final String M_ADD_TIMESTAMP= "MADD_TIMESTAMP";
    public static final String M_UPDATE_TIMESTAMP= "MUPDATE_TIMESTAMP";
    public static final String M_SITE= "MSITE";


    //register image
    public static final String R_ID= "RID";
    public static final String R_NAME= "RNAME";
    public static final String R_PASS= "RPASS";
    public static final String R_IMAGE= "RIMAGE";
    public static final String R_ADD_TIMESTAMP= "RADD_TIMESTAMP";
    public static final String R_UPDATE_TIMESTAMP= "RUPDATE_TIMESTAMP";


    public DataBaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        String CREATE_MAIN_PROJECT = "CREATE TABLE " + TABLE_Main_Project + " ("
                + PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + START_Time + " TEXT,"
                + PROJECT_NAME + " TEXT,"
                + CUS_NAME + " TEXT,"
                + LOCATION + " TEXT,"
                + OTHER_INFO + " TEXT,"
                + EMPLOYEES + " TEXT,"
                + STOKES + " TEXT,"
                + MACHINES + " TEXT,"
                + ESTIMATED_TIME + " TEXT,"
                + ESTIMATED_COST + " TEXT,"
                + FINISHED + " TEXT"
                + ");";
        MyDB.execSQL(CREATE_MAIN_PROJECT);


        String CREATE_Customer = "CREATE TABLE " + TABLE_Customer + " ("
                + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + C_NAME + " TEXT,"
                + C_ADDRESS + " TEXT,"
                + C_PHONE + " TEXT,"
                + C_IMAGE + " TEXT,"
                + C_ADD_TIMESTAMP + " TEXT,"
                + C_UPDATE_TIMESTAMP + " TEXT,"
                + C_NIC + " TEXT,"
                 + C_DATE + " TEXT"
                 + ");";
        MyDB.execSQL(CREATE_Customer);




        String CREATE_Employeee = "CREATE TABLE " + TABLE_Employeee + " ("
                + E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + E_TYPE + " TEXT,"
                 + E_ROLE + " TEXT,"
                 + E_NAME + " TEXT,"
                 + E_IMAGE + " TEXT,"
                 + E_ADD_TIMESTAMP + " TEXT,"
                 + E_UPDATE_TIMESTAMP + " TEXT,"
                 + E_ADDRESS + " TEXT,"
                 + E_PHONE + " TEXT,"
                 + E_NIC + " TEXT,"
                 + E_GENDER + " TEXT,"
                + E_SITENAME + " TEXT,"
                + E_SALARY + " FLOAT,"
                + E_DATE + " TEXT,"
                + E_FULL_SALARY + " FLOAT,"
                + E_PAID_SALARY + " FLOAT"
                + ");";
        MyDB.execSQL(CREATE_Employeee);



        String CREATE_Vehicle = "CREATE TABLE " + TABLE_Vehicle + " ("
                + V_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + V_NU + " TEXT,"
                 + V_NAME + " TEXT,"
                 + V_TYPE + " TEXT,"
                  + V_IMAGE + " TEXT,"
                 + V_ADD_TIMESTAMP + " TEXT,"
                 + V_UPDATE_TIMESTAMP + " TEXT"
                + ");";
        MyDB.execSQL(CREATE_Vehicle);




       String CREATE_Stoke  = "CREATE TABLE " + TABLE_Stoke  + " ("
                + S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + S_NAME + " TEXT,"
                 + S_QTY + " TEXT,"
                 + S_IMAGE + " TEXT,"
                 + S_ADD_TIMESTAMP + " TEXT,"
                 + S_UPDATE_TIMESTAMP + " TEXT,"
                 + S_DATE + " TEXT,"
                 + S_SALARY + " TEXT,"
                 + S_SITENAME + " TEXT"
                + ");";
              MyDB.execSQL(CREATE_Stoke);



        String CREATE_Machine  = "CREATE TABLE " + TABLE_Machine   + " ("
                + M_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + M_NUM + " TEXT,"
                 + M_NAME + " TEXT,"
                 + M_IMAGE + " TEXT,"
                 + M_ADD_TIMESTAMP + " TEXT,"
                 + M_UPDATE_TIMESTAMP + " TEXT,"
                 + M_SITE + " TEXT"
                + ");";
        MyDB.execSQL(CREATE_Machine);




        String CREATE_Register  = "CREATE TABLE " + TABLE_Register    + " ("
                + R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + R_NAME + " TEXT,"
                + R_PASS + " TEXT,"
                + R_IMAGE + " TEXT,"
                + R_ADD_TIMESTAMP + " TEXT,"
                + R_UPDATE_TIMESTAMP + " TEXT"
                + ");";
        MyDB.execSQL(CREATE_Register);





    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL(" DROP TABLE IF EXISTS " +TABLE_Customer  );
        MyDB.execSQL(" DROP TABLE IF EXISTS " +TABLE_Employeee  );
        MyDB.execSQL(" DROP TABLE IF EXISTS " +TABLE_Vehicle  );
        MyDB.execSQL(" DROP TABLE IF EXISTS " +TABLE_Stoke  );
        MyDB.execSQL(" DROP TABLE IF EXISTS " +TABLE_Machine  );
        MyDB.execSQL(" DROP TABLE IF EXISTS " +TABLE_Register  );
        MyDB.execSQL(" DROP TABLE IF EXISTS " +TABLE_Main_Project  );
        onCreate(MyDB);



    }


    public void updatePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(R_NAME,username);
        values.put(R_PASS,password);
        MyDB.update("Register", values,"Rname=?",new String[]{username});

    }

    public boolean checkUsername( String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from Register where Rname = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }



    public Boolean checkUsernameassword(String username, String password) {
       SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery ("Select * from Register where Rname = ? and Rpass = ?",new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }



    //Add MainProject DAta to Db

    public long addMainProject(MainProject mainProject){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(START_Time,mainProject.getStarted());
        values.put(PROJECT_NAME,mainProject.getProjectName());
        values.put(CUS_NAME,mainProject.getCustomerName());
        values.put(LOCATION,mainProject.getLocation());
        values.put(OTHER_INFO,mainProject.getOthers());
        values.put(EMPLOYEES,mainProject.getEmployees());
        values.put(STOKES,mainProject.getStokes());
        values.put(MACHINES,mainProject.getMachines());
        values.put(ESTIMATED_TIME,mainProject.geteTime());
        values.put(ESTIMATED_COST,mainProject.geteCost());
        values.put(FINISHED,mainProject.getFinished());

        //save to table
        long CID = MyDB.insert(TABLE_Main_Project ,null,values);
        MyDB.close();
        return CID;
    }

    //get all Projects into a list
    public List<MainProject>  getAllProjects(){

        List<MainProject> mainProjects=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_Main_Project;

        Cursor cursor=db.rawQuery(query,null);


        if (cursor.moveToFirst()){
            do {
                MainProject mainProject=new MainProject();

                mainProject.setId(cursor.getInt(0));
                mainProject.setStarted(cursor.getLong(1));
                mainProject.setProjectName(cursor.getString(2));
                mainProject.setCustomerName(cursor.getString(3));
                mainProject.setLocation(cursor.getString(4));
                mainProject.setOthers(cursor.getString(5));
                mainProject.setEmployees(cursor.getString(6));
                mainProject.setStokes(cursor.getString(7));
                mainProject.setMachines(cursor.getString(8));
                mainProject.seteTime(cursor.getString(9));
                mainProject.seteCost(cursor.getString(10));
                mainProject.setFinished(cursor.getLong(11));

                mainProjects.add(mainProject);
            }while (cursor.moveToNext());

        }
        return mainProjects;
    }

//Delete Projecr
    public void deleteProject(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_Main_Project,PROJECT_ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }






//custom

    public long insertInfo(String name,String address,String phone,String image,String addTimeStamp,String updateTimeStamp,String nic,String date){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_NAME,name);
        values.put(C_ADDRESS,address);
        values.put(C_PHONE,phone);
        values.put(C_IMAGE,image);
        values.put(C_ADD_TIMESTAMP,addTimeStamp);
        values.put(C_UPDATE_TIMESTAMP,updateTimeStamp);
        values.put(C_NIC,nic);
        values.put(C_DATE,date);

        long CID = MyDB.insert(TABLE_Customer ,null,values);
        MyDB.close();
        return CID;
    }


    public long insertInfoe(String Etype,String Erole,String Ename,String Eimage,String EaddTimeStamp,String EupdateTimeStamp,String Eaddress,String Ephone,String Enic,String Egender){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(E_TYPE,Etype);
        values.put(E_ROLE,Erole);
        values.put(E_NAME,Ename);
        values.put(E_IMAGE,Eimage);
        values.put(E_ADD_TIMESTAMP,EaddTimeStamp);
        values.put(E_UPDATE_TIMESTAMP,EupdateTimeStamp);
        values.put(E_ADDRESS,Eaddress);
        values.put(E_PHONE ,Ephone);
        values.put(E_NIC,Enic);
        values.put(E_GENDER,Egender);


        long EID = MyDB.insert(TABLE_Employeee ,null,values);
        MyDB.close();
        return EID;
    }

    public long insertInfov(String Vnu,String Vname,String Vimage,String VaddTimeStamp,String VupdateTimeStamp,String Vtype){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(V_NU,Vnu);
        values.put(V_NAME,Vname);
        values.put(V_IMAGE,Vimage);
        values.put(V_ADD_TIMESTAMP,VaddTimeStamp);
        values.put(V_UPDATE_TIMESTAMP,VupdateTimeStamp);
        values.put(V_TYPE,Vtype);

        long VID = MyDB.insert(TABLE_Vehicle ,null,values);
        MyDB.close();
        return VID;
    }


    public long insertInfoS(String Sname,String Sqty,String Simage,String SaddTimeStamp,String SupdateTimeStamp,String Sdate,String Ssalary,String Ssitename){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(S_NAME,Sname);
        values.put(S_QTY,Sqty);
        values.put(S_IMAGE,Simage);
        values.put(S_ADD_TIMESTAMP,SaddTimeStamp);
        values.put(S_UPDATE_TIMESTAMP,SupdateTimeStamp);
        values.put(S_DATE,Sdate);
        values.put(S_SALARY,Ssalary);
        values.put(S_SITENAME,Ssitename);



        long SID = db.insert(TABLE_Stoke ,null,values);
        db.close();
        return SID;
    }

    public long insertInfoM(String Mnum,String Mname,String Simage,String SaddTimeStamp,String SupdateTimeStamp,String site){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(M_NUM,Mnum);
        values.put(M_NAME,Mname);
        values.put(M_IMAGE,Simage);
        values.put(M_ADD_TIMESTAMP,SaddTimeStamp);
        values.put(M_UPDATE_TIMESTAMP,SupdateTimeStamp);
        values.put(M_SITE,site);


        long MID = db.insert(TABLE_Machine ,null,values);
        db.close();
        return MID;
    }


    public long insertInfoR(String Rname,String Rpass,String Rimage,String RaddTimeStamp, String RaddTimeupdate){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(R_NAME,Rname);
        values.put(R_PASS,Rpass);
        values.put(R_IMAGE,Rimage);
        values.put(R_ADD_TIMESTAMP,RaddTimeStamp);
        values.put(R_UPDATE_TIMESTAMP,RaddTimeupdate);



        long RID = db.insert(TABLE_Register ,null,values);
        db.close();
        return RID;
    }

    public int updateEmployeeInfo(Employee employee)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_TYPE, employee.getE_TYPE());
        contentValues.put(E_ROLE, employee.getE_ROLE());
        contentValues.put(E_NAME, employee.getE_NAME());
        contentValues.put(E_IMAGE, employee.getE_IMAGE());
        contentValues.put(E_ADDRESS, employee.getE_ADDRESS());
        contentValues.put(E_PHONE, employee.getE_PHONE());
        contentValues.put(E_NIC, employee.getE_NIC());
        contentValues.put(E_GENDER, employee.getE_GENDER());
        contentValues.put(E_ADD_TIMESTAMP, employee.getE_ADD_TIMESTAMP());
        contentValues.put(E_UPDATE_TIMESTAMP, employee.getE_UPDATE_TIMESTAMP());
        contentValues.put(E_SITENAME, employee.getE_SITENAME());
        contentValues.put(E_SALARY, employee.getE_SALARY());
        contentValues.put(E_DATE, employee.getE_DATE());
        contentValues.put(E_FULL_SALARY, employee.getE_FULL_SALARY());
        contentValues.put(E_PAID_SALARY, employee.getE_PAID_SALARY());

        int status = db.update(TABLE_Employeee,contentValues, E_ID +" =?",
                new String[]{String.valueOf(employee.getE_ID())});

        db.close();
        return status;

    }

    public int updateCustomerInfo(Customer customer)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(C_NAME, customer.getC_NAM());
        contentValues.put(C_ADDRESS, customer.getC_ADDRESS());
        contentValues.put(C_PHONE, customer.getC_PHONE());
        contentValues.put(C_IMAGE, customer.getC_IMAGE());
        contentValues.put(C_ADD_TIMESTAMP, customer.getC_ADD_TIMESTAMP());
        contentValues.put(C_UPDATE_TIMESTAMP, customer.getC_UPDATE_TIMESTAMP());
        contentValues.put(C_NIC, customer.getC_NIC());
        contentValues.put(C_DATE, customer.getC_DATE());

        int status = db.update(TABLE_Customer,contentValues, C_ID +" =?",
                new String[]{String.valueOf(customer.getC_ID())});

        db.close();
        return status;

    }

    public int updateVehicleInfo(Vehicle vehicle)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(V_NU, vehicle.getV_NU());
        contentValues.put(V_NAME, vehicle.getV_NAME());
        contentValues.put(V_TYPE, vehicle.getV_TYPE());
        contentValues.put(V_IMAGE, vehicle.getV_IMAGE());
        contentValues.put(V_ADD_TIMESTAMP, vehicle.getV_ADD_TIMESTAMP());
        contentValues.put(V_UPDATE_TIMESTAMP, vehicle.getV_UPDATE_TIMESTAMP());

        int status = db.update(TABLE_Vehicle,contentValues, V_ID +" =?",
                new String[]{String.valueOf(vehicle.getV_ID())});

        db.close();
        return status;

    }

    public int updateMachineInfo(Machine machine)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(M_NUM, machine.getM_NUM());
        contentValues.put(M_NAME, machine.getM_NAME());
        contentValues.put(M_IMAGE, machine.getM_IMAGE());
        contentValues.put(M_ADD_TIMESTAMP, machine.getM_ADD_TIMESTAMP());
        contentValues.put(M_UPDATE_TIMESTAMP, machine.getM_UPDATE_TIMESTAMP());
        contentValues.put(M_SITE, machine.getM_SITE());

        int status = db.update(TABLE_Machine,contentValues, M_ID +" =?",
                new String[]{String.valueOf(machine.getM_ID())});

        db.close();
        return status;

    }

    public int updateStockInfo(Stoke stock)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(S_NAME, stock.getS_NAME());
        contentValues.put(S_QTY, stock.getS_QTY());
        contentValues.put(S_IMAGE, stock.getS_IMAGE());
        contentValues.put(S_ADD_TIMESTAMP, stock.getS_ADD_TIMESTAMP());
        contentValues.put(S_UPDATE_TIMESTAMP, stock.getS_UPDATE_TIMESTAMP());
        contentValues.put(S_DATE, stock.getS_DATE());
        contentValues.put(S_SALARY, stock.getS_SALARY());
        contentValues.put(S_SITENAME, stock.getS_SITENAME());

        int status = db.update(TABLE_Stoke,contentValues, S_ID +" =?",
                new String[]{String.valueOf(stock.getS_ID())});

        db.close();
        return status;

    }

    public List<Employee>getAllEmployees(String name){

        List<Employee> empListList = new ArrayList();
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery ("Select * from Employeee where ENAME Like ?",new String[] {"%"+ name +"%"});

        if(cursor.moveToFirst())
        {
            do {
                Employee employee = new Employee();

                employee.setE_ID(cursor.getInt(0));
                employee.setE_TYPE(cursor.getString(1));
                employee.setE_ROLE(cursor.getString(2));
                employee.setE_NAME(cursor.getString(3));
                employee.setE_IMAGE(cursor.getString(4));
                employee.setE_ADD_TIMESTAMP(cursor.getString(5));
                employee.setE_UPDATE_TIMESTAMP(cursor.getString(6));
                employee.setE_ADDRESS(cursor.getString(7));
                employee.setE_PHONE(cursor.getString(8));
                employee.setE_NIC(cursor.getString(9));
                employee.setE_GENDER(cursor.getString(10));
                employee.setE_SITENAME(cursor.getString(11));
                employee.setE_SALARY(cursor.getFloat(12));
                employee.setE_DATE(cursor.getString(13));
                employee.setE_FULL_SALARY(cursor.getFloat(14));
                employee.setE_PAID_SALARY(cursor.getFloat(15));

                empListList.add(employee);
            }while (cursor.moveToNext());
        }
        return empListList;
    }

    public List<Customer>getAllCustomers(String name){

        List<Customer> customerList = new ArrayList();
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery ("Select * from Customer where NAME Like ?",new String[] {"%"+ name +"%"});

        if(cursor.moveToFirst())
        {
            do {
                Customer customer = new Customer();

                customer.setC_ID(cursor.getInt(0));
                customer.setC_NAM(cursor.getString(1));
                customer.setC_ADDRESS(cursor.getString(2));
                customer.setC_PHONE(cursor.getString(3));
                customer.setC_IMAGE(cursor.getString(4));
                customer.setC_ADD_TIMESTAMP(cursor.getString(5));
                customer.setC_UPDATE_TIMESTAMP(cursor.getString(6));
                customer.setC_NIC(cursor.getString(7));
                customer.setC_DATE(cursor.getString(8));

                customerList.add(customer);
            }while (cursor.moveToNext());
        }
        return customerList;
    }

    public List<Vehicle>getAllVehicle(String name){

        List<Vehicle> vehicleList = new ArrayList();
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery ("Select * from Vehicle where VNAME Like ?",new String[] {"%"+ name +"%"});

        if(cursor.moveToFirst())
        {
            do {
                Vehicle vehicle = new Vehicle();

                vehicle.setV_ID(cursor.getInt(0));
                vehicle.setV_NU(cursor.getString(1));
                vehicle.setV_NAME(cursor.getString(2));
                vehicle.setV_TYPE(cursor.getString(3));
                vehicle.setV_IMAGE(cursor.getString(4));
                vehicle.setV_ADD_TIMESTAMP(cursor.getString(5));
                vehicle.setV_UPDATE_TIMESTAMP(cursor.getString(6));

                vehicleList.add(vehicle);
            }while (cursor.moveToNext());
        }
        return vehicleList;
    }

    public List<Stoke>getAllStoke(String name){

        List<Stoke>stokeList = new ArrayList();
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery ("Select * from Stoke where SNAME Like ?",new String[] {"%"+ name +"%"});

        if(cursor.moveToFirst())
        {
            do {
                Stoke stoke = new Stoke();

                stoke.setS_ID(cursor.getInt(0));
                stoke.setS_NAME(cursor.getString(1));
                stoke.setS_QTY(cursor.getString(2));
                stoke.setS_IMAGE(cursor.getString(3));
                stoke.setS_UPDATE_TIMESTAMP(cursor.getString(4));
                stoke.setS_ADD_TIMESTAMP(cursor.getString(5));
                stoke.setS_DATE(cursor.getString(6));
                stoke.setS_SALARY(cursor.getString(7));
                stoke.setS_SITENAME(cursor.getString(8));

                stokeList.add(stoke);
            }while (cursor.moveToNext());
        }
        return stokeList;
    }

    public List<Machine>getAllMachines(String name){

        List<Machine> machineList = new ArrayList();
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor =MyDB.rawQuery ("Select * from Machine where MNAME Like ?",new String[] {"%"+ name +"%"});

        if(cursor.moveToFirst())
        {
            do {
                Machine machine = new Machine();

                machine.setM_ID(cursor.getInt(0));
                machine.setM_NUM(cursor.getString(1));
                machine.setM_NAME(cursor.getString(2));
                machine.setM_IMAGE(cursor.getString(3));
                machine.setM_UPDATE_TIMESTAMP(cursor.getString(4));
                machine.setM_ADD_TIMESTAMP(cursor.getString(5));
                machine.setM_SITE(cursor.getString(6));

                machineList.add(machine);
            }while (cursor.moveToNext());
        }
        return machineList;
    }

//for load to edit get single data in Mainprojects
    public MainProject getSingleProject(int Id){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor =MyDB.query (TABLE_Main_Project,new String[]{PROJECT_ID,START_Time,PROJECT_NAME,CUS_NAME,LOCATION,OTHER_INFO,EMPLOYEES,STOKES,MACHINES,ESTIMATED_TIME,ESTIMATED_COST,FINISHED},
                PROJECT_ID + "= ?",new String[]{String.valueOf(Id)},null,null,null);


        MainProject mainProject;
        if(cursor!=null){
            cursor.moveToFirst();
            mainProject = new MainProject(
                    cursor.getInt(0),
                    cursor.getLong(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getLong(11)
            );
            return mainProject;
        }
        return null;
    }

    //update a single data in MainProject Table
    public int updateSingleData(MainProject mainProject){
        SQLiteDatabase MyDB= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(START_Time,mainProject.getStarted());
        values.put(PROJECT_NAME,mainProject.getProjectName());
        values.put(CUS_NAME,mainProject.getCustomerName());
        values.put(LOCATION,mainProject.getLocation());
        values.put(OTHER_INFO,mainProject.getOthers());
        values.put(EMPLOYEES,mainProject.getEmployees());
        values.put(STOKES,mainProject.getStokes());
        values.put(MACHINES,mainProject.getMachines());
        values.put(ESTIMATED_TIME,mainProject.geteTime());
        values.put(ESTIMATED_COST,mainProject.geteCost());
        values.put(FINISHED,mainProject.getFinished());

        int status=MyDB.update(TABLE_Main_Project,values,PROJECT_ID +" =?",new String[]{String.valueOf(mainProject.getId())});
        MyDB.close();
        return status;

    }

    //Delete Employee
    public void deleteEmployee(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_Employeee,E_ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Customer
    public void deleteCustomer(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_Customer,C_ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Vehicle
    public void deleteVehicle(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_Vehicle,V_ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Machine
    public void deleteMachine(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_Machine,M_ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Delete Stock
    public void deleteStock(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_Stoke,S_ID +" =?", new String[]{String.valueOf(id)});
        db.close();
    }

}
