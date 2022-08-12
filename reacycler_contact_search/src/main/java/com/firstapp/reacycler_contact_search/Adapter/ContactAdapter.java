package com.firstapp.reacycler_contact_search.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.reacycler_contact_search.Model.ContactModel;
import com.firstapp.reacycler_contact_search.R;
import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.holder>{
    Context context;
    List<ContactModel> contactModelList=new ArrayList<>();

    AlertDialog alertDialog;


    public ContactAdapter(Context context, List<ContactModel> contactModelList) {
        this.context = context;
        this.contactModelList = contactModelList;
    }

    @NonNull
    @Override
    public ContactAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());

        View root=layoutInflater.inflate(R.layout.custom_contatcs,parent,false);

        return new holder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.holder holder, int i) {

        ContactModel contactModel=contactModelList.get(i);

        holder.name.setText(contactModelList.get(i).getName());
        holder.number.setText(contactModelList.get(i).getNumber());
        holder.shapeableImageView.setImageURI(contactModelList.get(i).getImageUri());

        if (contactModelList.get(i).getImage()!=null)
        {
            holder.shapeableImageView.setImageURI(Uri.parse(""+contactModelList.get(i).getImage()));

        }
        else
        {
            Log.i(null,""+contactModelList.get(i).getImage());

        }

    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
         ShapeableImageView shapeableImageView;
         TextView name;
         TextView number;


        public holder(@NonNull View itemView) {
            super(itemView);

            shapeableImageView=itemView.findViewById(R.id.contact_shap);
            name=itemView.findViewById(R.id.contact_name);
            number=itemView.findViewById(R.id.contact_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    View view=LayoutInflater.from(context).inflate(R.layout.custom_alretdialog,null);
                    ImageView imageView1=view.findViewById(R.id.contact_alert);
                    TextView alert_con_name=view.findViewById(R.id.contact_alretname);
                    alert_con_name.setText(name.getText().toString());

                    imageView1.setImageURI(Uri.parse(""+contactModelList.get(getAdapterPosition()).getImage()));

                    builder.setView(view);
                    builder.setCancelable(false);

                    ImageView imacolse=view.findViewById(R.id.imageclose1);
                     imacolse.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             alertDialog.dismiss();
                         }
                     });
                     alertDialog=builder.create();
                     alertDialog.show();
                }
            });

        }
    }
}
