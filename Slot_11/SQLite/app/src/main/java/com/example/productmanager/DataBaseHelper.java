package com.example.productmanager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.productmanager.Product;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "goods.db";
    private static final int DATABASE_VERSION = 1;
    protected static final String TABLE_PRODUCT = "product";
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_PRODUCT_NAME = "product_name";
    protected static final String COLUMN_PRODUCT_DESCRIPTION = "product_description";
    protected static final String COLUMN_PRODUCT_PRICE = "product_price";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_PRODUCT + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_PRODUCT_DESCRIPTION + " TEXT," +
                COLUMN_PRODUCT_PRICE + " REAL" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }


    public long insertProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_PRODUCT_DESCRIPTION, product.getProductDescription());
        values.put(COLUMN_PRODUCT_PRICE, product.getProductPrice());

        long newRowId = db.insert(TABLE_PRODUCT, null, values);
        db.close();

        return newRowId;
    }

    public SQLiteDatabase getDatabase() {
        return this.getWritableDatabase();
    }
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCT, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                @SuppressLint("Range") String productDescription = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION));
                @SuppressLint("Range") double productPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE));

                Product product = new Product(id, productName, productDescription, productPrice);
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productList;
    }

    public int updateProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_PRODUCT_DESCRIPTION, product.getProductDescription());
        values.put(COLUMN_PRODUCT_PRICE, product.getProductPrice());

        int rowsAffected = db.update(TABLE_PRODUCT, values, COLUMN_ID + "=?", new String[]{String.valueOf(product.getId())});
        db.close();

        return rowsAffected;
    }

    public int deleteProduct(Product product) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(TABLE_PRODUCT, COLUMN_ID + "=?", new String[]{String.valueOf(product.getId())});
        db.close();

        return rowsAffected;
    }
}
