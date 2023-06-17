package com.example.productmanager;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        adapter = new ProductAdapter(productList);

        recyclerView.setAdapter(adapter);

        databaseHelper = new DataBaseHelper(this);

        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        productList.clear();

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String[] projection = {
                DataBaseHelper.COLUMN_ID,
                DataBaseHelper.COLUMN_PRODUCT_NAME,
                DataBaseHelper.COLUMN_PRODUCT_DESCRIPTION,
                DataBaseHelper.COLUMN_PRODUCT_PRICE
        };

        Cursor cursor = database.query(
                DataBaseHelper.TABLE_PRODUCT,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_ID));
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_PRODUCT_NAME));
                @SuppressLint("Range") String productDescription = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_PRODUCT_DESCRIPTION));
                @SuppressLint("Range") double productPrice = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COLUMN_PRODUCT_PRICE));

                Product product = new Product(id, productName, productDescription, productPrice);
                productList.add(product);
            } while (cursor.moveToNext());

            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No products found", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }
}
