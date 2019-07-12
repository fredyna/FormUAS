package com.fredynurapriyanto.formuas.retrofit;

import com.fredynurapriyanto.formuas.model.DataItem;
import com.fredynurapriyanto.formuas.model.ResponseUas;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("uas")
    Call<ResponseUas> getDataUas();

    @FormUrlEncoded
    @POST("uas")
    Call<DataItem> simpanData(
            @Field("nik") String nik, @Field("nama") String nama,
            @Field("kelas") String kelas, @Field("jam") String jam
    );
}
