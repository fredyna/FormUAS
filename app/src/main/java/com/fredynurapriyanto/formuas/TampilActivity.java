package com.fredynurapriyanto.formuas;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fredynurapriyanto.formuas.model.DataItem;
import com.fredynurapriyanto.formuas.model.ResponseUas;
import com.fredynurapriyanto.formuas.retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilActivity extends AppCompatActivity {

    List<DataItem> data = new ArrayList<>();
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        recycler = findViewById(R.id.recyclerView);

        getDataUas();

        recycler.setAdapter(new UasAdapter(TampilActivity.this,data));
        recycler.setLayoutManager(new LinearLayoutManager(TampilActivity.this));
    }

    private void getDataUas() {
        final ProgressDialog progress = new ProgressDialog(TampilActivity.this);
        progress.setMessage("waiting...");
        progress.show();

        Call<ResponseUas> request = RetrofitConfig.getApiService().getDataUas();
        request.enqueue(new Callback<ResponseUas>() {
            @Override
            public void onResponse(Call<ResponseUas> call, Response<ResponseUas> response) {
                progress.dismiss();
                if (response.isSuccessful()){
                    data = response.body().getData();
                    recycler.setAdapter(new UasAdapter(TampilActivity.this,data));
                } else {
                    Toast.makeText(TampilActivity.this, "Request not success",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUas> call, Throwable t) {

            }
        });
    }
}
