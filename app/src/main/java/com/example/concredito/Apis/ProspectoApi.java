package com.example.concredito.Apis;

import com.example.concredito.Activities.Prospecto;
import com.example.concredito.Objetos.ProspectoObj;
import com.example.concredito.Objetos.StatusObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ProspectoApi {

    String API_ROUTE = ".";

    @GET("prospecto?")
    Call< ProspectoObj > getProspecto(@Query("id") String idProspecto);

    @POST("prospecto")
    Call< ProspectoObj > postProspecto(@Body ProspectoObj prospecto);

    @PUT("prospecto")
    Call<StatusObj> putProspecto(@Body StatusObj status);

}