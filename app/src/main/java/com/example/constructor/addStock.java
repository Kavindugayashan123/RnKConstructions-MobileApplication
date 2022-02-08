package com.example.constructor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Calendar;

import static com.example.constructor.Home.myStr;

public class addStock extends AppCompatActivity {

   private EditText Sname,Sqty,Ssalary,Ssitename;
   TextView Sdate;
    private ImageView imgClose,pImageView;
   private Button btnStock;
    AwesomeValidation awesomeValidation;

    ActionBar actionBar;

    private static final int CAMERA_REQUEST_CODE=100;
    private static final int STORAGE_REQUEST_CODE=101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE=103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;

    private  String name,qty,date,salary,sitename,timeStamp;
    private DataBaseHelper dbHelper;
    Dialog dialog;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    String user;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        getSupportActionBar().hide();

        actionBar=getSupportActionBar();
        actionBar.setTitle("Add Information");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Window window=this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.myStatusBar));



        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.St_name, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.St_qty, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.St_date, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.St_salary, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.St_siteName, RegexTemplate.NOT_EMPTY,R.string.not_null);


        pImageView=findViewById(R.id.S_personImage);
        Sname=findViewById(R.id.St_name);
        Sqty=findViewById(R.id.St_qty);
        Sdate=findViewById(R.id.St_date);
        Ssalary=findViewById(R.id.St_salary);
        Ssitename=findViewById(R.id.St_siteName);
        btnStock=findViewById(R.id.btnStoke);
        imgClose=findViewById(R.id.btnClose);


        Ssitename.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                int qty=0,item=0;

                    qty=Integer.parseInt(Sqty.getText().toString());
                    item=Integer.parseInt(Ssalary.getText().toString());
                    //Total=Integer.parseInt( * ;
                    System.out.println(qty+item);
                    //Ssitename.setText(Total);
                Ssitename.setText("Rs. "+(qty*item));
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();


            }
        });




        dbHelper = new DataBaseHelper(this);
        dialog=new Dialog(this);



        //date picker

        Sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(addStock.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectDate=+dayOfMonth+ "/" +month+ "/" +year;
                Sdate.setText(selectDate);
            }
        };


        cameraPermissions =new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions  = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


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

        btnStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                }
            }
        });


  pImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagePickDialog();

            }
        });
        btnStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {
                    if(Sdate.getText().toString().equals("Assign Date")){
                        Toast.makeText(getApplicationContext(),"Please Select Date !",Toast.LENGTH_SHORT).show();
                    }else {
                        openDilogSuccessfull();
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                closeDilogSuccessfull();
                                getData();

                            }
                        },1500);

                    }
                }


            }


        });
    }


    private void getData() {

        name = ""+Sname.getText().toString().trim();
        qty = ""+Sqty.getText().toString().trim();
        date = ""+Sdate.getText().toString().trim();
        timeStamp = ""+System.currentTimeMillis();
        salary = ""+Ssalary.getText().toString().trim();
        sitename = ""+Ssitename.getText().toString().trim();

        long SID = dbHelper.insertInfoS(
                ""+name,
                ""+qty,
                ""+imageUri,
                ""+timeStamp,
                ""+timeStamp,
                ""+date,
                ""+salary,
                ""+sitename

        );
        //Toast.makeText(this,"Record added to id:"+SID,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), addStock.class);
        startActivity(intent);
    }

     private void imagePickDialog() {
                String[] options={"Camera", "Gallery"};
                final AlertDialog.Builder  builder= new AlertDialog.Builder(this);

                builder.setTitle("Sel]]]]]]]ect for image");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(which == 0){
                            if(!checkCameraPermission()){
                                requestCameraPermission();
                            }
                            else {
                                pickFromCamera();
                            }
                        }
                        else if (which == 1){
                            if(!checkStoragePermission()){
                                requestStoragePermission();

                            }
                            else{
                                pickFromStorage();
                            }


                        }



                    }

        });
                builder.create().show();

            }

    private void pickFromStorage() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,IMAGE_PICK_GALLERY_CODE);

    }

    private void pickFromCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image description");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(cameraIntent,IMAGE_PICK_GALLERY_CODE);


    }

    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                ==(PackageManager.PERMISSION_GRANTED);

        boolean resultl = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==(PackageManager.PERMISSION_GRANTED);
        return result && resultl;
    }

    private void  requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]permissions, @Nullable int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case  CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }
                    else{
                        Toast.makeText(this, "Camera Permission required!", Toast.LENGTH_SHORT).show();

                    }

                }
            }
            break;

            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted){
                        pickFromCamera();
                    }
                    else {
                        Toast.makeText(this, "Storage permission requires!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){

            if(requestCode == IMAGE_PICK_GALLERY_CODE){

                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){

                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }

            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                if(resultCode == RESULT_OK){
                    Uri resultUri = result.getUri();
                    imageUri= resultUri;
                    pImageView.setImageURI(resultUri);

                }
                else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
                }
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
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