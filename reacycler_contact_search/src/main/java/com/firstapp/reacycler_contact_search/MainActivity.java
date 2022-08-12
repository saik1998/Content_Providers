package com.firstapp.reacycler_contact_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.reacycler_contact_search.Adapter.ContactAdapter;
import com.firstapp.reacycler_contact_search.Model.ContactModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class MainActivity extends AppCompatActivity{

    RecyclerView recycler;
    ContactModel contactModel;
    ContactAdapter contactAdapter;
    List<ContactModel> contactModelList=new ArrayList<>();

    List<ContactModel> filterlist =new ArrayList<>();

    Cursor cursor;
    ContentResolver contentResolver;
    SearchView searchView;

    AlertDialog alertDialog;

    TextView count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler=findViewById(R.id.recycler_v);
        searchView=findViewById(R.id.search_view);
        count=findViewById(R.id.text_view_count);


        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 101);
        }
        else
        {
            readAllContacts();

            searchView.setQueryHint("Search among"+" "+contactModelList.size()+" "+"contact(s)");//count will be adding the search view

            searchView.clearFocus();



            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {

                    filterlist.clear();

                    if(s.toString().isEmpty())
                    {
                        count.setVisibility(View.GONE);//once search the count count will automatically  gone.

                        recycler.setAdapter(new ContactAdapter(getApplicationContext(),contactModelList));
                        contactAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        Filter(s.toString());


                    }
                    return true;
                }
            });

            recycler.setHasFixedSize(true);
            recycler.setLayoutManager(new LinearLayoutManager(this));

            contactAdapter=new ContactAdapter(this,contactModelList);
            recycler.setAdapter(contactAdapter);

        }
    }
    private void Filter(String text)
    {
        count.setVisibility(View.VISIBLE);//whenever you want see the all contacts in search view

        for (ContactModel post: contactModelList)
        {
            if(post.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filterlist.add(post);
            }
        }
        recycler.setAdapter(new ContactAdapter(getApplicationContext(),filterlist));

        contactAdapter.notifyDataSetChanged();

        count.setText(" "+filterlist.size()+ " "+ "CONTATCS FOUND");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 101:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    readAllContacts();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;


            }
        }

    private void readAllContacts() {

        contactModelList.clear();
        contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_URI};
        String selection = null;
        String[] args = null;
        String order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " asc";

        cursor = contentResolver.query(uri, projection, selection, args, order);
//
//        Cursor cursor =  managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, null, null);


        if (cursor.getCount() > 0 && cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                @SuppressLint("Range") String photo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI));


                contactModel=new ContactModel(name,number,photo);
                contactModelList.add(contactModel);


                contactAdapter = new ContactAdapter(getApplicationContext(), contactModelList);
                recycler.setAdapter(contactAdapter);


            }
        } else {
            Toast.makeText(MainActivity.this, "No contacts found in your phone", Toast.LENGTH_SHORT).show();
        }
    }

}
