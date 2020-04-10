package com.apps.spendingtrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper database;
    RecyclerView recyclerView;
    ItemAdapter adapter;
    ArrayList<ItemProperty> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new DatabaseHelper(this);
        arrayList = new ArrayList<>();
        lihatData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new ItemAdapter(arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Perbarui data
        adapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                arrayList.get(position);
                adapter.notifyItemChanged(position);
                Intent intent = new Intent(MainActivity.this, PerbaruiActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
                finish();
            }
        });
    }

    private void lihatData() {
        Cursor data = database.ambilData();
        if (data.getCount() == 0) {

        }
        while (data.moveToNext()) {
            String listId = data.getString(0);
            String listNama = data.getString(1);
            String listNominal = data.getString(2);
            String listTanggal = data.getString(3);
            arrayList.add(new ItemProperty(listId, listNama, ("Rp" + listNominal), listTanggal));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_tambah:
                Intent intent = new Intent(this, TambahActivity.class);
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
