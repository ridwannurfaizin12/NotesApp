package com.example.notesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class AdapterDaftarNotes extends BaseAdapter {

    private List<Data> data;
    private Context context;
    private int id;
    private AlertDialog daftarCatatan;

    public AdapterDaftarNotes(List<Data> data, Context context, AlertDialog daftarCatatan) {
        this.data = data;
        this.context = context;
        this.daftarCatatan = daftarCatatan;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_notes, null);

        TextView itemJudul = view.findViewById(R.id.itemJudul);
        ImageView editCatatan = view.findViewById(R.id.editCatatan);
        ImageView hapusCatatan = view.findViewById(R.id.hapusCatatan);

        final Data Data1 = data.get(position);
        itemJudul.setText(Data1.getJudul());

        //Metode klik untuk melihat catatan
        itemJudul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("judul", Data1.getJudul());
                i.putExtra("catatan", Data1.getCatatan());
                i.putExtra("lihat", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                daftarCatatan.dismiss();
            }
        });

        //metode klik untuk menyunting catatan, dengan mengirimkan intent ke aktivitas yang sama
        //setelah aktivitas di refresh, mode edit catatan aktif.
        editCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = Data1.getId();
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("id", id);
                i.putExtra("judul", Data1.getJudul());
                i.putExtra("catatan", Data1.getCatatan());
                i.putExtra("edit", true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                daftarCatatan.dismiss();
            }
        });

        //menghapus catatan satu-persatu
        hapusCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = Data1.getId();
                DBHelper dbHelper = new DBHelper(context);
                dbHelper.deleteNotes(id);
                notifyDataSetChanged();
                dbHelper.close();
                Toast.makeText(context, "Catatan dihapus", Toast.LENGTH_SHORT).show();
                daftarCatatan.dismiss();
            }
        });

        return view;
    }
}


