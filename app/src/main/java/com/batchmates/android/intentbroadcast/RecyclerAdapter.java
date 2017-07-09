package com.batchmates.android.intentbroadcast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 7/8/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<PersonObject> contactList=new ArrayList<>();

    public RecyclerAdapter(ArrayList<PersonObject> contactList) {
        this.contactList = contactList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView age;
        TextView gender;
        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            age=(TextView)itemView.findViewById(R.id.age);
            gender=(TextView)itemView.findViewById(R.id.gender);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PersonObject personObject=contactList.get(position);

        holder.name.setText(personObject.getName());
        holder.age.setText(String.valueOf(personObject.getAge()));
        holder.gender.setText(personObject.getGender());

    }

    @Override
    public int getItemCount() {

        return contactList.size();
    }

}
