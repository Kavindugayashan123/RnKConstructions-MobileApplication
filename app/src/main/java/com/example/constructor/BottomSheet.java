package com.example.constructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {
LinearLayout emplLayout,cuslLayout,stocklLayout,vechiclelLayout,machineLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.bottom_sheet,container,false);


        emplLayout = v.findViewById(R.id.addEmp);
        cuslLayout = v.findViewById(R.id.addCus);
        stocklLayout = v.findViewById(R.id.addStock);
        vechiclelLayout = v.findViewById(R.id.addVeh);
        machineLayout = v.findViewById(R.id.addMachine);


        emplLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),addEmployee.class);
                startActivity(intent);
            }
        });
        cuslLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),addCustomer.class);
                startActivity(intent);
            }
        });
        stocklLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),addStock.class);
                startActivity(intent);
            }
        });
        vechiclelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),addVehicle.class);
                startActivity(intent);
            }
        });
        machineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),addMachine.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
