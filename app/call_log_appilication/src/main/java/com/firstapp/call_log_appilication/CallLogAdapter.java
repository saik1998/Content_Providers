package com.firstapp.call_log_appilication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.CallHolder> {
    Context context;
    List<CallLogModel>callLogModelList=new ArrayList<>();

    LayoutInflater layoutInflater;

    public CallLogAdapter(Context context, List<CallLogModel> callLogModelList) {
        this.context = context;
        this.callLogModelList = callLogModelList;

        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @NonNull
    @Override
    public CallLogAdapter.CallHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_call_log,parent,false);

        ShapeableImageView shapeableImageView=root.findViewById(R.id.contact_image);

        return new CallHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CallLogAdapter.CallHolder holder, int position) {

        holder.name.setText(callLogModelList.get(position).getName());

        holder.number.setText(callLogModelList.get(position).getNumber());

        holder.time.setText(callLogModelList.get(position).getTime());

        holder.duration.setText(callLogModelList.get(position).getDuration());

//        holder.type.setText(callLogModelList.get(position).getType());

        holder.image.setText((CharSequence) callLogModelList.get(position).getImage());

//        holder.imageUri.setImageResource(callLogModelList.get(position).getImageUri());


        if(callLogModelList.get(position).getType().equals("1"))
        {
            holder.type.setText("INCOMING_TYPE");
        }
        else if(callLogModelList.get(position).getType().equals("2"))
        {
            holder.type.setText("OUTGOING_TYPE");
        }
        else if(callLogModelList.get(position).getType().equals("3"))
        {
            holder.type.setText("MISSED_TYPE");
        }
        else if(callLogModelList.get(position).getType().equals("4"))
        {
            holder.type.setText("VOICEMAIL_TYPE");
        }
        else if(callLogModelList.get(position).getType().equals("5"))
        {
            holder.type.setText("REJECTED_TYPE");
        }
        else if(callLogModelList.get(position).getType().equals("6"))
        {
            holder.type.setText("BLOCKED_TYPE");
        }
        else if(callLogModelList.get(position).getType().equals("7"))
        {
            holder.type.setText("ANSWERED_EXTERNALLY_TYPE");
        }

    }

    @Override
    public int getItemCount() {
        return callLogModelList.size();
    }

    public class CallHolder extends RecyclerView.ViewHolder {

        TextView name,number,time,duration,type,image;

        ImageView imageUri;
        public CallHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.conatct_name);
            number=itemView.findViewById(R.id.conatct_number);
            time=itemView.findViewById(R.id.contact_date);
            duration=itemView.findViewById(R.id.contact_duration);
            type=itemView.findViewById(R.id.contact_type);
            image=itemView.findViewById(R.id.contact_image);


        }
    }
}
