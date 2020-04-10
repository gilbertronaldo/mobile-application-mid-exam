package com.apps.spendingtrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PerbaruiActivity extends AppCompatActivity {

    EditText txt_updateNama, txt_updateNominal;
    Button btn_perbarui;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perbarui);
        txt_updateNama = findViewById(R.id.txt_updateNama);
        txt_updateNominal = findViewById(R.id.txt_updateNominal);
        btn_perbarui = findViewById(R.id.btn_perbarui);
        database = new DatabaseHelper(this);
        perbaruiData();
    }

    private void perbaruiData() {
        btn_perbarui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txt_updateNama.getText().toString().isEmpty() && !txt_updateNominal.getText().toString().isEmpty()) {
                    int id;
                    Bundle bd = getIntent().getExtras();
                    if (bd == null) {
                        id = 1;
                    } else {
                        id = bd.getInt("id");
                    }
                    String listId = Integer.toString(id + 1);
                    String nama = txt_updateNama.getText().toString();
                    String nominal = txt_updateNominal.getText().toString();
                    boolean isUpdated = database.perbaruiData(listId, nama, nominal);
                    if (isUpdated) {
                        Toast.makeText(PerbaruiActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PerbaruiActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(PerbaruiActivity.this, "Data gagal diperbarui", Toast.LENGTH_SHORT).show();
                    }
                } else if (!txt_updateNama.getText().toString().isEmpty() && txt_updateNominal.getText().toString().isEmpty()) {
                    txt_updateNominal.setError("Isi kolom ini");
                } else if (txt_updateNama.getText().toString().isEmpty() && !txt_updateNominal.getText().toString().isEmpty()) {
                    txt_updateNama.setError("Isi kolom ini");
                } else {
                    txt_updateNama.setError("Isi kolom ini");
                    txt_updateNominal.setError("Isi kolom ini");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_kembali:
                Intent intent = new Intent(PerbaruiActivity.this, MainActivity.class);
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
