package com.fredynurapriyanto.formuas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fredynurapriyanto.formuas.model.DataItem;

import java.util.ArrayList;
import java.util.List;

public class UasAdapter extends RecyclerView.Adapter<UasAdapter.MyHolder> {
    private Context context;
    private List<DataItem> data = new ArrayList<>();

    public UasAdapter(Context context, List<DataItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.tvNik.setText(data.get(i).getNik());
        myHolder.tvNama.setText(data.get(i).getNama());
        myHolder.tvKelas.setText(data.get(i).getKelas());
        myHolder.tvJam.setText(data.get(i).getJam());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvNik;
        TextView tvNama;
        TextView tvKelas;
        TextView tvJam;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvNik = itemView.findViewById(R.id.tv_nik);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvKelas = itemView.findViewById(R.id.tv_kelas);
            tvJam = itemView.findViewById(R.id.tv_jam);
        }
    }
}
