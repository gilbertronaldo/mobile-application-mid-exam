package com.apps.spendingtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TambahActivity extends AppCompatActivity {

    EditText txt_nama, txt_nominal;
    Button btn_submit;
    DatabaseHelper database;
    ArrayList<ItemProperty> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        database = new DatabaseHelper(this);
        txt_nama = findViewById(R.id.txt_nama);
        txt_nominal = findViewById(R.id.txt_nominal);
        btn_submit = findViewById(R.id.btn_submit);
        tambahData();
    }

    public void tambahData(){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txt_nama.getText().toString().isEmpty() && !txt_nominal.getText().toString().isEmpty()){
                    String nama = txt_nama.getText().toString();
                    String nominal = txt_nominal.getText().toString();
                    Date calendar = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
                    String tanggal = df.format(calendar);
                    boolean isInserted = database.tambahData(nama, nominal, tanggal);
                    if(isInserted){
                        Toast.makeText(TambahActivity.this, "Data berhasil ditambahkan",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TambahActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else{
                        Toast.makeText(TambahActivity.this, "Data gagal ditambahkan",Toast.LENGTH_SHORT).show();
                    }
                } else if (!txt_nama.getText().toString().isEmpty() && txt_nominal.getText().toString().isEmpty()){
                    txt_nominal.setError("Isi kolom ini");
                } else if (txt_nama.getText().toString().isEmpty() && !txt_nominal.getText().toString().isEmpty()){
                    txt_nama.setError("Isi kolom ini");
                } else {
                    txt_nama.setError("Isi kolom ini");
                    txt_nominal.setError("Isi kolom ini");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.icon_kembali:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.icon);
        builder.setMessage("Ingin keluar dari aplikasi?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
