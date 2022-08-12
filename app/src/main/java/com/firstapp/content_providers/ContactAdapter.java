package com.firstapp.content_providers;

import static androidx.recyclerview.widget.RecyclerView.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Context context;
    Activity activity;
    ArrayList<ContactModel> contactModelArrayList;
    ArrayList<ContactModel> listful;

    public ContactAdapter(Context context, ArrayList<ContactModel> contactModelArrayList, ArrayList<ContactModel> listful) {
        this.context = context;
        this.activity = activity;
        this.contactModelArrayList = contactModelArrayList;
        this.listful = listful;
        this.contactModelArrayList = new ArrayList<>(listful);

        notifyDataSetChanged();
    }

    public ContactAdapter(Context applicationContext, List<ContactModel> contactModelList) {
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_contacts, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int i) {

        ContactModel model = contactModelArrayList.get(i);

        holder.contactname.setText(model.getName());
        holder.contactnumber.setText(model.getNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "tel:" + model.getNumber();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(s));
                activity.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return contactModelArrayList.size();
    }


    public Filter getFilter() {
        return contactFilter;

    }

    private final Filter contactFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<ContactModel> contactFilterlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                contactFilterlist.addAll(listful);


            } else {
                String filter = constraint.toString().trim();
                for (ContactModel contactModel : listful) {
                    if (contactModel.getName().toLowerCase().contains(filter.toUpperCase()))
                        contactFilterlist.add(contactModel);
                }
            }
            FilterResults results = new FilterResults();
            results.values = contactFilterlist;
            results.count = contactFilterlist.size();

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contactModelArrayList.clear();

            contactModelArrayList.addAll((Collection<? extends ContactModel>) results.values);
            notifyDataSetChanged();
        }

    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView contactname, contactnumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contactname = itemView.findViewById(R.id.contact_name);
            contactnumber = itemView.findViewById(R.id.contact_number);

        }
    }
    }









