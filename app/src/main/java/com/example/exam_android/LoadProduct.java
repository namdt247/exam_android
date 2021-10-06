package com.example.exam_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.exam_android.database.DBHelper;

public class LoadProduct extends AppCompatActivity {

    private DBHelper db;
    private Cursor c;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_product);

        db = new DBHelper(this);
        ListView lvProduct = findViewById(R.id.lvProduct);

        c = db.getAllProduct();

        adapter = new SimpleCursorAdapter(this, R.layout.item_product, c, new String[]{
                DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY}, new int[]{R.id.tvId, R.id.tvName,
                R.id.tvQuantity}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        lvProduct.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        c = db.getAllProduct();
        adapter.changeCursor(c);
        adapter.notifyDataSetInvalidated();
        db.close();
    }
}