package com.fredynurapriyanto.formuas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fredynurapriyanto.formuas.model.DataItem;
import com.fredynurapriyanto.formuas.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etNik;
    private EditText etNama;
    private Spinner spKelas;
    private EditText etJam;

    private Button btnDaftar;
    private Button btnTampil;

    String[] mKelas = {"6A", "6B", "6C", "6D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNik = findViewById(R.id.et_nik);
        etNama = findViewById(R.id.et_nama);
        spKelas = findViewById(R.id.sp_kelas);
        etJam = findViewById(R.id.et_jam);
        btnDaftar = findViewById(R.id.btn_daftar);
        btnTampil = findViewById(R.id.btn_tampil);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mKelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKelas.setAdapter(adapter);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TampilActivity.class);
                startActivity(intent);
            }
        });
    }

    public void simpanData(){
        String nik = etNik.getText().toString();
        String nama = etNama.getText().toString();
        String kelas = spKelas.getSelectedItem().toString();
        String jam = etJam.getText().toString();

        if(nik.isEmpty()){
            etNik.setError("NIK tidak boleh kosong");
        } else if(nama.isEmpty()){
            etNama.setError("Nama tidak boleh kosong");
        } else if(jam.isEmpty()){
            etJam.setError("Jam tidak boleh kosong");
        } else if(kelas.isEmpty()){
            Toast.makeText(MainActivity.this, "Kelas Belum dipilih", Toast.LENGTH_SHORT).show();
        } else{
            final ProgressDialog progress = new ProgressDialog(MainActivity.this);
            progress.setMessage("waiting to save...");
            progress.show();


            Call<DataItem> request = RetrofitConfig.getApiService()
                    .simpanData(nik, nama, kelas, jam);
            request.enqueue(new Callback<DataItem>() {
                @Override
                public void onResponse(Call<DataItem> call, Response<DataItem> response) {
                    progress.dismiss();
                    if(response.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Berhasil Simpan Data", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(MainActivity.this, "Gagal Simpan Data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DataItem> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Network Failure! "+t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }

    public void setKosong(){
        etNik.setText("");
        etNama.setText("");
        etJam.setText("");
    }

}
