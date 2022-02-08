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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Arrays;
import java.util.List;

import static com.example.constructor.Home.myStr;

public class addEmployee extends AppCompatActivity {

    ImageView imgClose,pImageView;
    AwesomeValidation awesomeValidation;
    Button btnemployee;
    EditText E_type,E_role,E_name,E_address,E_phone,E_nic,E_gender;
    List<String> selectGender=Arrays.asList("Select Gender","Male","Female");
    List<String> selectEmpType= Arrays.asList("Select Employee Type","Labor","Sub Contractor");
    List<String> roleEmpType= Arrays.asList("Select Employee Role","Estimator","Architect","Supervisor","Worker","Engineer","Electrician","Foreman"
    ,"Carpenter","Sub Ironworker","Joiner","Plumber","Painter","Pipefitter","Signal Worker","Roofer","Welder","Installer","Cleaner","Surveyor","Inspector","operator");
    Dialog dialog;

    ActionBar action;

    private static final int CAMERA_REQUEST_CODE=100;
    private static final int STORAGE_REQUEST_CODE=101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE=103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;

    private  String type,role,name,address,phone,nic,gender,timeStamp;
    private  Spinner selectTypeSpinner,selectRoleSpinner,selectGenderSpinner;
    private DataBaseHelper dbHelper;

    String user;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        getSupportActionBar().hide();


          action=getSupportActionBar();
          action.setTitle("Add Information");
          action.setDisplayShowHomeEnabled(true);
          action.setDisplayHomeAsUpEnabled(true);

        Window window=this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.myStatusBar));

        dialog=new Dialog(this);

        //Select Type , Role, Gender

        selectTypeSpinner=findViewById(R.id.Em_Type1);
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,selectEmpType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTypeSpinner.setAdapter(adapter);

        selectRoleSpinner=findViewById(R.id.Em_Role1);
        ArrayAdapter adapter1=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,roleEmpType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectRoleSpinner.setAdapter(adapter1);

        selectGenderSpinner=findViewById(R.id.Em_Gender1);
        ArrayAdapter adapter2=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,selectGender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectGenderSpinner.setAdapter(adapter2);

        //String empTYPE=selectTypeSpinner.getSelectedItem().toString();


        pImageView=findViewById(R.id.E_personImage);
        //E_type=findViewById(R.id.Em_Type);
        //E_role=findViewById(R.id.Em_Role);
        E_name=findViewById(R.id.Em_Name);
        E_address=findViewById(R.id.Em_Address);
        E_phone=findViewById(R.id.Em_Phone);
        E_nic=findViewById(R.id.Em_NIC);
        //E_gender=findViewById(R.id.Em_Gender);


        imgClose=findViewById(R.id.btnClose);
        btnemployee=findViewById(R.id.addnewEmployee);
      //  Spinner mySpinner = findViewById(R.id.spinner2);

       // ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(addEmployee.this,
           //     android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
      //  myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // mySpinner.setAdapter(myAdapter);


        dbHelper = new DataBaseHelper(this);


        cameraPermissions =new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions  = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};




        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //awesomeValidation.addValidation(this,R.id.Em_Type1, RegexTemplate.NOT_EMPTY,R.string.not_null);
        //awesomeValidation.addValidation(this,R.id.Em_Role, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.Em_Name, RegexTemplate.NOT_EMPTY,R.string.not_null);
        awesomeValidation.addValidation(this,R.id.Em_Address, RegexTemplate.NOT_EMPTY,R.string.not_null);
       awesomeValidation.addValidation(this,R.id.Em_Phone, RegexTemplate.NOT_EMPTY,R.string.not_null);
       awesomeValidation.addValidation(this,R.id.Em_NIC, RegexTemplate.NOT_EMPTY,R.string.not_null);
        //awesomeValidation.addValidation(this,R.id.Em_Gender, RegexTemplate.NOT_EMPTY,R.string.not_null);


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


        pImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagePickDialog();

            }
        });

        btnemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {

                    if(selectTypeSpinner.getSelectedItem().toString()=="Select Employee Type" || selectRoleSpinner.getSelectedItem().toString()=="Select Employee Role" || selectGenderSpinner.getSelectedItem().toString()=="Select Gender"){
                        Toast.makeText(getApplicationContext(),"Select Correct Details",Toast.LENGTH_SHORT).show();
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

        type = ""+selectTypeSpinner.getSelectedItem().toString().trim();
        role = ""+selectRoleSpinner.getSelectedItem().toString().trim();
        name = ""+E_name.getText().toString().trim();
        address = ""+E_address.getText().toString().trim();
        timeStamp = ""+System.currentTimeMillis();
       phone = ""+E_phone.getText().toString().trim();
        nic = ""+E_nic.getText().toString().trim();
        gender = ""+selectGenderSpinner.getSelectedItem().toString().trim();

        long EID = dbHelper.insertInfoe(
                ""+type,
                ""+role,
                ""+name,
                ""+imageUri,
                ""+timeStamp,
                ""+timeStamp,
                ""+address,
                ""+phone,
                ""+nic,
                ""+gender

        );
       //Toast.makeText(this,"Record added to id:"+EID,Toast.LENGTH_SHORT).show();
       //  Toast.makeText(this, "sign in successful", Toast.LENGTH_SHORT).show();

         Intent intent = new Intent(getApplicationContext(), addEmployee.class);
         startActivity(intent);

    }

     private void imagePickDialog() {
                String[] options={"Camera", "Gallery"};
                final AlertDialog.Builder  builder= new AlertDialog.Builder(this);

                builder.setTitle("Select for image");
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

