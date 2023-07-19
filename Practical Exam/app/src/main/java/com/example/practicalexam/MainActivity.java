package com.example.practicalexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicalexam.DBContext.ContactDataSource;
import com.example.practicalexam.Model.Contact;
import com.example.practicalexam.Model.ContactAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_NEW_CONTACT = 1;
    private RecyclerView recyclerViewContacts;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList;

    private ContactDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ContactDataSource
        dataSource = new ContactDataSource(this);
        dataSource.open();

        // Load contacts from the database
        contactList = dataSource.getAllContacts();

        // Close the database connection
        dataSource.close();

        contactAdapter = new ContactAdapter(contactList);

        recyclerViewContacts.setAdapter(contactAdapter);

        FloatingActionButton buttonAddContact = findViewById(R.id.buttonAddContact);
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivityForResult(intent, REQUEST_CODE_NEW_CONTACT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_NEW_CONTACT && resultCode == RESULT_OK) {
            // Fetch the updated contacts from the database
            dataSource.open();
            contactList.clear();
            contactList.addAll(dataSource.getAllContacts());
            dataSource.close();

            // Notify the adapter of the data change
            contactAdapter.notifyDataSetChanged();
        }
    }
}

