package com.example.exam_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exam_android.database.DBHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edName;
    private EditText edQuantity;
    private Button btAdd;
    private Button btView;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edQuantity = findViewById(R.id.edQuantity);
        btAdd = findViewById(R.id.btAdd);
        btView = findViewById(R.id.btView);

        btAdd.setOnClickListener(this);
        btView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btAdd:
                onAdd();
                break;
            case R.id.btView:
                onView();
                break;
            default:
                break;
        }
    }

    private void onAdd() {
        if (edName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter Name product", Toast.LENGTH_LONG).show();
            return;
        }

        if (edQuantity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter Quantity product", Toast.LENGTH_LONG).show();
            return;
        }

        String regex = "[0-9]+";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(edQuantity.getText().toString());

        if (m.matches() == false) {
            Toast.makeText(this, "Quantity product only number", Toast.LENGTH_LONG).show();
            return;
        }

        String isAdd = db.addProduct(edName.getText().toString(), edQuantity.getText().toString());
        Toast.makeText(this, isAdd, Toast.LENGTH_LONG).show();

//        Intent intent = new Intent(MainActivity.this, LoadProduct.class);
//        startActivity(intent);
    }

    private void onView() {
        Intent intent = new Intent(MainActivity.this, LoadProduct.class);
        startActivity(intent);
    }
}