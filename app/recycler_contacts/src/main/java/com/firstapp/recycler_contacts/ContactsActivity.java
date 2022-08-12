package com.firstapp.recycler_contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {


    Contact1Adapter contact1Adapter;
    Contact1Model contact1Model;


    TextView contactload;


    List<Contact1Model> contact1ModelList = new ArrayList<>();



    RecyclerView recyclerView;
    ContentResolver content1Resolver;
    Cursor cursor;

    Button button;


    SearchView searchView;
    List<Contact1Model> filterlist = new ArrayList<>();


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.recycler_v);
        searchView = findViewById(R.id.search_view);


//        contact1Adapter = new Contact1Adapter(this, contact1ModelList);
//        recyclerView.setAdapter(contact1Adapter);
        {

            if (ContextCompat.checkSelfPermission(ContactsActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ContactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 101);

            } else {
                readAllContacts();

                contactload.setText("contactfound" + "" + contact1ModelList.size());

//                    contact1ModelList.clear();
//                    contactload.findFocus();
//                    contactload.clearFocus();

            }
            contactload = findViewById(R.id.text_view_count);


//        recyclerView = findViewById(R.id.recycler_v);

            searchView = findViewById(R.id.search_view);

            searchView.clearFocus();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filterlist(newText);

                    return false;
                }
            });

            recyclerView = findViewById(R.id.recycler_v);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

            contact1Adapter = new Contact1Adapter(this, contact1ModelList);
            recyclerView.setAdapter(contact1Adapter);
        }
    }
    private void filterlist(String newText) {
        List<Contact1Model> filteredList = new ArrayList<>();

        for (Contact1Model post : contact1ModelList) {

            if (post.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(post);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data wiil found", Toast.LENGTH_SHORT).show();
        } else {
            contact1Adapter.setFilteredList(filteredList);
            contactload.setText("Total Count" + filteredList.size());


        }


        recyclerView.setAdapter(new Contact1Adapter(getApplicationContext(), filteredList));
        contact1Adapter.notifyDataSetChanged();
        contactload.setText("count:" + filteredList.size());
    }
    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults){

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 101:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readAllContacts();
                } else {
                    Toast.makeText(ContactsActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }




        private void readAllContacts() {
        contact1ModelList.clear();
        content1Resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_URI};
        String selection = null;
        String[] args = null;
        String order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " asc";

        cursor = content1Resolver.query(uri, projection, selection, args, order);
//
//        Cursor cursor =  managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, null, null);


        if (cursor.getCount() > 0 && cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                @SuppressLint("Range") String photo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI));


                contact1Model = new Contact1Model(name, number, photo);
                contact1ModelList.add(contact1Model);


                contact1Adapter = new Contact1Adapter(getApplicationContext(), contact1ModelList);
                recyclerView.setAdapter(contact1Adapter);


            }
        } else {
            Toast.makeText(this, "No contacts found in your phone", Toast.LENGTH_SHORT).show();
        }


//    button = findViewById(R.id.contact_btn);




//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                filterlist(s);
//                filterlist.clear();
//                if (s.toString().isEmpty()) {
//                    recyclerView.setAdapter(new Contact1Adapter(getApplicationContext(), contact1ModelList));
//                    contact1Adapter.notifyDataSetChanged();
//                } else {
//                    filterlist(s.toString());
//                }
//
//
//                return true;
//            }
//
//
//        });



//        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {


//        });


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                permissionMethod();
//
//
//            }

    }




//        recyclerView.setAdapter(new Contact1Adapter(getApplicationContext(), filterlist));
//        contact1Adapter.notifyDataSetChanged();
//
//        contactload.setText("contact found"+""+filterlist.size());
////        contactload.findFocus();
//
//



    }




//    @Override
//    public void onCLick(int i) {
////
//
//        Toast.makeText(this, "Data selected", Toast.LENGTH_SHORT).show();
//
//    }








