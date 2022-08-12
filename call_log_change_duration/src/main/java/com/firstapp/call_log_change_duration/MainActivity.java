package com.firstapp.call_log_change_duration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


        RecyclerView recyclerView_calllog;
        CallLogModel callLogModel;
        CallLogAdapter callLogAdapter;
        List<CallLogModel> callLogModelList=new ArrayList<>();

        //Content Providers
        Cursor cursor;
        ContentResolver contentResolver;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            recyclerView_calllog=findViewById(R.id.recyclerView);

            recyclerView_calllog.setHasFixedSize(true);
            recyclerView_calllog.setLayoutManager(new LinearLayoutManager(this));

            callLogAdapter=new CallLogAdapter(this,callLogModelList);
            recyclerView_calllog.setAdapter(callLogAdapter);

            findViewById(R.id.CallLog).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    permissionMethod();
                }
            });
        }

        private void permissionMethod() {

            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CALL_LOG},201);
            }
            else
            {
                loadallCallLogs();
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode)
            {
                case 201:
                    if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    {
                        loadallCallLogs();
                    }
                    else
                    {
                        Toast.makeText(this, "Call Log Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    public static String convertSecond(int seconds) {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        String sh = (h > 0 ? String.valueOf(h) + " " + "h" : " ");
        String sm = (h < 10 && m > 0 && h > 0 ? "0" : " ") + (m > 0 ? (h > 0 && s == 0 ? String.valueOf(m) : String.valueOf(m) + " " + "min") : "");
        String ss = (s == 0 && (h > 0 || m > 0) ? "" : (s < 10 && (h > 0 || m > 0) ? "0" : "") + String.valueOf(s) + " " + "sec");

        return sh + (h > 0 ? " " : "") + sm + (m > 0 ? " " : "") + ss;
    }

    int seconds = 3661;
    String duration1 = convertSecond(seconds);




        private void loadallCallLogs() {

            contentResolver=getContentResolver();

            Uri uri= CallLog.Calls.CONTENT_URI;
            String[] projections={CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE};
            String selection=null;
            String[] args=null;
            String order=""+ CallLog.Calls.DATE+" DESC";

            cursor=contentResolver.query(uri,projections,selection,args,order);

            if(cursor.getCount()>0&&cursor!=null)
            {
                while (cursor.moveToNext())
                {
                    @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                    @SuppressLint("Range") String number=cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                    @SuppressLint("Range") String time=cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                    @SuppressLint("Range") String duration=cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
                    @SuppressLint("Range") String type=cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));


                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy =   HH:mm");
                    String dateString = formatter.format(new Date(Long.parseLong(time)));



                    callLogModel=new CallLogModel(name,number,dateString,duration,type);
                    callLogModelList.add(callLogModel);
                    callLogAdapter.notifyDataSetChanged();
                }

            }

            else
            {
                Toast.makeText(this, "No Call Logs Found", Toast.LENGTH_SHORT).show();
            }



        }
    }


