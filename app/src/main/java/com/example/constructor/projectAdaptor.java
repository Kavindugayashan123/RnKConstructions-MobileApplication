package com.example.constructor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class projectsAdaptor extends ArrayAdapter<MainProject> {

    private Context context;
    private int resource;
    private List<MainProject> mainProjects;
    projectsAdaptor(Context context, int resource, List<MainProject> mainProjects){
        super(context,resource,mainProjects);
        this.context = context;
        this.resource = resource;
        this.mainProjects = mainProjects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View row=inflater.inflate(resource,parent,false);

        TextView proj_name= row.findViewById(R.id.projectName);
        TextView owner= row.findViewById(R.id.owner);
        TextView started= row.findViewById(R.id.started);
        ImageView imageView=row.findViewById(R.id.imageView9);

        //mainprojects
        MainProject mainProject=mainProjects.get(position);
        proj_name.setText(mainProject.getProjectName());
        owner.setText(mainProject.getLocation());
        started.setText("Owner :"+mainProject.getCustomerName());

        if(mainProject.getFinished()>0){
            imageView.setVisibility(View.VISIBLE);

        }


        return row;
    }
}
