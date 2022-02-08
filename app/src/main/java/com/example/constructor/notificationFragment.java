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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.List;


public class notificationFragment extends Fragment {


    Context context;
    private ListView listView;
    ImageView SearchBtn;
    private Button btnsalary;
    private EditText searchitem;
    AwesomeValidation awesomeValidation;
    private String name;
    private List<Employee> employeeList;
    private DataBaseHelper dataBaseHelper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_notification,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();


        Window window=((Activity)view.getContext()).getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.myStatusBar));
        context = this.getActivity();

        SearchBtn = view.findViewById(R.id.search_Btn2);
        searchitem = view.findViewById(R.id.searchItemEmp);
        listView= view.findViewById(R.id.Search_List2);
        dataBaseHelper = new DataBaseHelper(this.getActivity());

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    name = "" + searchitem.getText().toString().trim();
                    employeeList = dataBaseHelper.getAllEmployees(name);
                SearchAdapterEmp adapter = new SearchAdapterEmp(context, R.layout.row, employeeList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getContext(), EmployeeSalary.class);
                            i.putExtra("Employee", employeeList.get(position));
                            startActivity(i);

                        }
                    });

                }});
        return view;

    }

}

class SearchAdapterEmp extends ArrayAdapter<Employee> {
    Context context = this.getContext();
    private int resource;
    private List<Employee> employeeList;

    SearchAdapterEmp(Context context, int resource, List<Employee> employeeList){
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
