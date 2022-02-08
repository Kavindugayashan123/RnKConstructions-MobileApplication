package com.example.constructor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private ListView listView;
    private String name;
    private EditText searchitem;
    private DataBaseHelper dataBaseHelper;
    Context context ;
    private List<Employee> employeeList;
    private List<Customer> customerList;
    private List<Vehicle> vehicleList;
    private List<Machine> machineList;
    private List<Stoke> stokeList;
    ImageView SearchBtn;
    CheckBox EmpChk, CusChk, MchneChk, VehChk, StkChk;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();


        Window window=((Activity)view.getContext()).getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.myStatusBar));
        context = this.getActivity();

        dataBaseHelper = new DataBaseHelper(this.getActivity());
        listView= view.findViewById(R.id.Search_List);
        searchitem = view.findViewById(R.id.searchItem);
        SearchBtn = view.findViewById(R.id.search_Btn);
        EmpChk = view.findViewById(R.id.EmpRdo);
        CusChk = view.findViewById(R.id.CusRdo);
        MchneChk = view.findViewById(R.id.MachineRdo);
        VehChk = view.findViewById(R.id.VehicleRdo);
        StkChk = view.findViewById(R.id.StockRdo);
        employeeList  = new ArrayList<>();
        customerList  = new ArrayList<>();
        vehicleList  = new ArrayList<>();
        machineList  = new ArrayList<>();
        stokeList  = new ArrayList<>();


        EmpChk.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
               CusChk.setEnabled(false);
               MchneChk.setEnabled(false);
               VehChk.setEnabled(false);
               StkChk.setEnabled(false);

                  if(!EmpChk.isChecked()){
                     CusChk.setEnabled(true);
                     MchneChk.setEnabled(true);
                    VehChk.setEnabled(true);
                     StkChk.setEnabled(true);
                     listView.setAdapter(null);
                    }
            }
        });
        CusChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmpChk.setEnabled(false);
                MchneChk.setEnabled(false);
                VehChk.setEnabled(false);
                StkChk.setEnabled(false);

                if(!CusChk.isChecked()){
                    EmpChk.setEnabled(true);
                    MchneChk.setEnabled(true);
                    VehChk.setEnabled(true);
                    StkChk.setEnabled(true);
                    listView.setAdapter(null);
                }
            }
        });
        MchneChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CusChk.setEnabled(false);
                EmpChk.setEnabled(false);
                VehChk.setEnabled(false);
                StkChk.setEnabled(false);

                if(!MchneChk.isChecked()){
                    CusChk.setEnabled(true);
                    EmpChk.setEnabled(true);
                    VehChk.setEnabled(true);
                    StkChk.setEnabled(true);
                    listView.setAdapter(null);
                }
            }
        });
        VehChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CusChk.setEnabled(false);
                MchneChk.setEnabled(false);
                EmpChk.setEnabled(false);
                StkChk.setEnabled(false);

                if(!VehChk.isChecked()){
                    CusChk.setEnabled(true);
                    MchneChk.setEnabled(true);
                    EmpChk.setEnabled(true);
                    StkChk.setEnabled(true);
                    listView.setAdapter(null);
                }
            }
        });
        StkChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CusChk.setEnabled(false);
                MchneChk.setEnabled(false);
                VehChk.setEnabled(false);
                EmpChk.setEnabled(false);

                if(!StkChk.isChecked()){
                    CusChk.setEnabled(true);
                    MchneChk.setEnabled(true);
                    VehChk.setEnabled(true);
                    EmpChk.setEnabled(true);
                    listView.setAdapter(null);
                }
            }
        });



        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EmpChk.isChecked())
                {
                    name = ""+searchitem.getText().toString().trim();
                    employeeList = dataBaseHelper.getAllEmployees(name);
                    SearchAdapter adapter = new SearchAdapter(context, R.layout.row, employeeList );
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getContext(), SearchItemDetail.class);
                            i.putExtra("Employee", employeeList.get(position));
                            startActivity(i);

                        }
                    });

                }
                else if (CusChk.isChecked())
                {
                    name = ""+searchitem.getText().toString().trim();
                    customerList = dataBaseHelper.getAllCustomers(name);
                    CustomerSearchAdapter adapter = new CustomerSearchAdapter(context, R.layout.row, customerList );
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getContext(), SearchItemDetail.class);
                            i.putExtra("Customer", customerList.get(position));
                            startActivity(i);

                        }
                    });

                }
                else if (VehChk.isChecked())
                {
                    name = ""+searchitem.getText().toString().trim();
                    vehicleList = dataBaseHelper.getAllVehicle(name);
                    VehicleSearchAdapter adapter = new VehicleSearchAdapter(context, R.layout.row, vehicleList );
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getContext(), SearchItemDetail.class);
                            i.putExtra("Vehicle", vehicleList.get(position));
                            startActivity(i);

                        }
                    });

                }

                else if (MchneChk.isChecked())
                {
                    name = ""+searchitem.getText().toString().trim();
                    machineList = dataBaseHelper.getAllMachines(name);
                    MachineSearchAdapter adapter = new MachineSearchAdapter(context, R.layout.row, machineList );
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getContext(), SearchItemDetail.class);
                            i.putExtra("Machine", machineList.get(position));
                            startActivity(i);

                        }
                    });

                }

                else if (StkChk.isChecked())
                {
                    name = ""+searchitem.getText().toString().trim();
                    stokeList = dataBaseHelper.getAllStoke(name);
                    StokeSearchAdapter adapter = new StokeSearchAdapter(context, R.layout.row, stokeList );
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getContext(), SearchItemDetail.class);
                            i.putExtra("Stock", stokeList.get(position));
                            startActivity(i);

                        }
                    });

                }


            }
        });
        return view;
    }
}


class SearchAdapter extends ArrayAdapter<Employee> {
    Context context = this.getContext();
    private int resource;
    private List<Employee> employeeList;

    SearchAdapter(Context context, int resource, List<Employee> employeeList){
        super(context, resource, employeeList);
        this.context = context;
        this.resource = resource;
        this.employeeList = employeeList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //LayoutInflater inflater = LayoutInflater.from(this.getContext());
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View raw = inflater.inflate(resource,parent, false);
        View raw = layoutInflater.inflate(R.layout.searchrow, parent, false);

        TextView Name = raw.findViewById(R.id.ItemName);
        TextView Address = raw.findViewById(R.id.ItemDesc);
        ImageView Image = raw.findViewById(R.id.image);

        Employee employee = employeeList.get(position);
        Name.setText(employee.getE_NAME());
        Address.setText(employee.getE_ADDRESS());
        Image.setImageURI(Uri.parse(employee.getE_IMAGE()));
        return raw;
    }
}


/*************************************************************************/

 class StokeSearchAdapter extends ArrayAdapter<Stoke> {
    Context context = this.getContext();
    private int resource;
    private List<Stoke> stokeList;

     StokeSearchAdapter(Context context, int resource, List<Stoke> stokeList){
        super(context, resource, stokeList);
        this.context = context;
        this.resource = resource;
        this.stokeList = stokeList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //LayoutInflater inflater = LayoutInflater.from(this.getContext());
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View raw = inflater.inflate(resource,parent, false);
        View raw = layoutInflater.inflate(R.layout.searchrow, parent, false);

        TextView Name = raw.findViewById(R.id.ItemName);
        TextView Address = raw.findViewById(R.id.ItemDesc);
        ImageView Image = raw.findViewById(R.id.image);

        Stoke stoke = stokeList.get(position);
        Name.setText(stoke.getS_NAME());
        Address.setText(stoke.getS_QTY());
        Image.setImageURI(Uri.parse(stoke.getS_IMAGE()));
        return raw;
    }
}
/*********************************/
class CustomerSearchAdapter extends ArrayAdapter<Customer> {
    Context context = this.getContext();
    private int resource;
    private List<Customer> customerList;


    CustomerSearchAdapter(Context context, int resource, List<Customer> customerList){
        super(context, resource, customerList);
        this.context = context;
        this.resource = resource;
        this.customerList = customerList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View raw = layoutInflater.inflate(R.layout.searchrow, parent, false);

        TextView Name = raw.findViewById(R.id.ItemName);
        TextView Address = raw.findViewById(R.id.ItemDesc);
        ImageView Image = raw.findViewById(R.id.image);

        Customer customer = customerList.get(position);
        Name.setText(customer.getC_NAM());
        Address.setText(customer.getC_ADDRESS());
        Image.setImageURI(Uri.parse(customer.getC_IMAGE()));
        return raw;
    }
}

class VehicleSearchAdapter extends ArrayAdapter<Vehicle> {
    Context context = this.getContext();
    private int resource;
    private List<Vehicle> vehicleList;


    VehicleSearchAdapter(Context context, int resource, List<Vehicle> vehicleList){
        super(context, resource, vehicleList);
        this.context = context;
        this.resource = resource;
        this.vehicleList = vehicleList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View raw = layoutInflater.inflate(R.layout.searchrow, parent, false);

        TextView Name = raw.findViewById(R.id.ItemName);
        TextView Address = raw.findViewById(R.id.ItemDesc);
        ImageView Image = raw.findViewById(R.id.image);

        Vehicle vehicle = vehicleList.get(position);
        Name.setText(vehicle.getV_NAME());
        Address.setText(vehicle.getV_NU());
        Image.setImageURI(Uri.parse(vehicle.getV_IMAGE()));
        return raw;
    }
}

class MachineSearchAdapter extends ArrayAdapter<Machine> {
    Context context = this.getContext();
    private int resource;
    private List<Machine> machineList;


    MachineSearchAdapter(Context context, int resource, List<Machine> machineList){
        super(context, resource, machineList);
        this.context = context;
        this.resource = resource;
        this.machineList = machineList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View raw = layoutInflater.inflate(R.layout.searchrow, parent, false);

        TextView Name = raw.findViewById(R.id.ItemName);
        TextView Address = raw.findViewById(R.id.ItemDesc);
        ImageView Image = raw.findViewById(R.id.image);

        Machine machine = machineList.get(position);
        Name.setText(machine.getM_NAME());
        Address.setText(machine.getM_NUM());
        Image.setImageURI(Uri.parse(machine.getM_IMAGE()));
        return raw;
    }
}
