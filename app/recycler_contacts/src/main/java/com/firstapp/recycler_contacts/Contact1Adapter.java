package com.firstapp.recycler_contacts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class Contact1Adapter extends RecyclerView.Adapter<Contact1Adapter.Contact1Holder> {

//    Activity activity;
//    ArrayList<Contact1Model> arrayList;
//    ArrayList<Contact1Model> listfull;
//

    Context context;
    AlertDialog alertDialog;
    OnItemClick onItemClick;
    List<Contact1Model> contact1ModelList = new ArrayList<>();
    LayoutInflater layoutInflater;

    TextView countitem;

    public Contact1Adapter(Context context, List<Contact1Model> contact1ModelList) {
        this.context = context;
        this.contact1ModelList = contact1ModelList;
//        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    public void setFilteredList(List<Contact1Model> filteredList) {
//        this.contact1ModelList=filteredList;
//        notifyDataSetChanged();
//    }


    //    public Contact1Adapter(Activity activity, ArrayList<Contact1Model> arrayList, ArrayList<Contact1Model> listfull) {
//        this.activity = activity;
//        this.arrayList = arrayList;
//        this.listfull = listfull;
//        this.arrayList=new ArrayList<>();
//
//        notifyDataSetChanged();


    @NonNull
    @Override
    public Contact1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.custom_contacts1, parent, false);
        return new Contact1Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Contact1Holder holder, @SuppressLint("RecyclerView") int i) {

        Contact1Model contact1Model = contact1ModelList.get(i);


        holder.contactname.setText(contact1ModelList.get(i).getName());
        holder.contactnumber.setText(contact1ModelList.get(i).getNumber());
        holder.shapeableImageView.setImageURI(contact1ModelList.get(i).imageUri);

        if (contact1ModelList.get(i).getImage() != null) {
            holder.shapeableImageView.setImageURI(Uri.parse("" + contact1ModelList.get(i).getImage()));

        } else {
            Log.i("null", "" + contact1ModelList.get(i).getImage());
        }


//                    ok.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Toast.makeText(Contact1Adapter.this.context,"Welcome", Toast.LENGTH_SHORT).show();
//                            alertDialog.dismiss();
//                        }
//
//                    });

    }

    @Override
    public int getItemCount() {

        return contact1ModelList.size();
    }

    public void setFilteredList(List<Contact1Model> filteredList) {
        this.contact1ModelList=filteredList;
        notifyDataSetChanged();
    }


//    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
//
//    }


    public class Contact1Holder extends RecyclerView.ViewHolder {
        TextView contactname, contactnumber;

        ShapeableImageView shapeableImageView;
        ImageView contactimage;

        public Contact1Holder(@NonNull View itemView) {

            super(itemView);
            contactname = itemView.findViewById(R.id.contact_name);
            contactnumber = itemView.findViewById(R.id.contact_number);
            shapeableImageView = itemView.findViewById(R.id.contact_shap);




            contactimage=itemView.findViewById(R.id.contact_alert);//alertdialog contact image

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            View view1 = LayoutInflater.from(context).inflate(R.layout.custom_alretdialog, null);
                            ShapeableImageView closeimg=view1.findViewById(R.id.contact_alert);


                            Button btn=view1.findViewById(R.id.cancel);
                            Button btn1=view1.findViewById(R.id.ok);

                            Button cross=view1.findViewById(R.id.imageclose1);



                            TextView alertName = view1.findViewById(R.id.contact_alretname);

                            alertName.setText(contactname.getText().toString());




                            alertName.setText(""+contact1ModelList.get(getAdapterPosition()).getName());


                            if(contact1ModelList.get(getAdapterPosition()).getImage()!=null)
                            {
                                closeimg.setImageURI(Uri.parse(" "+contact1ModelList.get(getAdapterPosition()).getImage()));
                            }
                            else
                            {
                                closeimg.setImageResource(R.drawable.ic_baseline_account_circle_24);

                            }
                            builder.setView(view1);
                            builder.setCancelable(false);


                            cross.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(Contact1Adapter.this.context, "Cancel", Toast.LENGTH_SHORT).show();


                                    alertDialog.dismiss();
                                }
                            });
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(Contact1Adapter.this.context, "Message", Toast.LENGTH_SHORT).show();
                                }
                            });
                            btn1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(Contact1Adapter.this.context, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });

                            alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
                        }


                    });





//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Toast.makeText(context, "Welcome" + contact1ModelList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
//
//



        }
    }
}




//            });
//        }
//    }
//}

//





































//    Context context;
//    List<Contact1Model> contactModelList=new ArrayList<>();
//    LayoutInflater inflater;
//
//    public Contact1Adapter(Context context, List<Contact1Model> contactModelList) {
//        this.context = context;
//        this.contactModelList = contactModelList;
//
//        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//
//    @Override
//    public int getCount() {
//        return contactModelList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return i;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View convertView, ViewGroup parent) {
//        View root=inflater.inflate(R.layout.custom_contacts1,null);
//
//        ShapeableImageView shapeableImageView=root.findViewById(R.id.contact_shap);
//        TextView name=root.findViewById(R.id.contact_name);
//        TextView number=root.findViewById(R.id.contact_number);
//
//
//        name.setText(contactModelList.get(i).getName());
//        number.setText(contactModelList.get(i).getNumber());
//
//        if(contactModelList.get(i).getImage()!=null)
//        {
//            shapeableImageView.setImageURI(Uri.parse(""+contactModelList.get(i).getImage()));
//        }
//        else
//        {
//            Log.i("null",""+contactModelList.get(i).getImage());
//        }
//
//        return root;
//
//        }

