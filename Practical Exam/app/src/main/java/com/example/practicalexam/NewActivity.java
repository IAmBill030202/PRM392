package com.example.practicalexam;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practicalexam.DBContext.ContactDataSource;
import com.example.practicalexam.Model.Contact;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });
    }

    private void saveContact() {
        // Retrieve data from EditText fields
        int empId = Integer.parseInt(((EditText) findViewById(R.id.editTextEmpID)).getText().toString());
        String empName = ((EditText) findViewById(R.id.editTextEmpName)).getText().toString();
        String empEmail = ((EditText) findViewById(R.id.editTextEmpEmail)).getText().toString();
        String empAddress = ((EditText) findViewById(R.id.editTextEmpAddress)).getText().toString();
        String empNotes = ((EditText) findViewById(R.id.editTextEmpNotes)).getText().toString();

        // Create a new Contact object with the entered data
        Contact newContact = new Contact(empId, empName, empEmail, empAddress, empNotes);

        // Pass the new Contact data back to the main activity
        Intent intent = new Intent();
        intent.putExtra("newContact", newContact);
        ContactDataSource dataSource = new ContactDataSource(this);
        dataSource.open();
        long insertedRowId = dataSource.insertContact(newContact);
        dataSource.close();
        if (insertedRowId != -1) {
            setResult(RESULT_OK);
        } else {
            Toast.makeText(this, "Failed to insert contact.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}

