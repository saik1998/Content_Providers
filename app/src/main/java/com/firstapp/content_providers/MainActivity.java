package com.firstapp.content_providers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    ContactAdapter contactAdapter;
    ContactModel contactModel;
    List<ContactModel> contactModelList=new ArrayList<>();
    LayoutInflater layoutInflater;
    
   RecyclerView recyclerView;
    ContentResolver contentResolver;
    Cursor cursor;



    List<ContactModel> filterlist=new ArrayList<>();


    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.contact_recycelr);







//        findViewById(R.id.contact_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},101);
//
//                }
//                else
//                {
//                    readAllContacts();
//                }
//
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case 101:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    readAllContacts();
                }
                else
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
//                    checkPermission();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.SerchView);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MIN_VALUE);
        searchView.setQueryHint("Serach Here");

//        searchView.setOnQueryTextFocusChangeListener(new OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                contactAdapter.getFilter().filter(newText)
//                return false;
//            }
//        });
//


        return super.onCreateOptionsMenu(menu);
    }

//    private void checkPermission() {
//    }

    private void readAllContacts() {
        contentResolver=getContentResolver();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Photo.PHOTO_URI};
        String selection=null;
        String[] selectionargs=null;
        String order=null;

        cursor=contentResolver.query(uri,projection,selection,selectionargs,order);


        if(cursor.getCount()>0&&cursor!=null)
        {
            while(cursor.moveToNext())
            {
                @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                @SuppressLint("Range") String photo=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI));



                contactModel=new ContactModel(name,number,photo);
                contactModelList.add(contactModel);


                contactAdapter=new ContactAdapter(getApplicationContext(),contactModelList);
                recyclerView.setAdapter(contactAdapter);


            }
        }

        else
        {
            Toast.makeText(this, "No contacts found in your phone", Toast.LENGTH_LONG).show();
        }


    }
}