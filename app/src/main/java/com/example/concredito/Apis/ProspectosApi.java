package com.example.concredito.Apis;

import com.example.concredito.Objetos.ProspectosObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProspectosApi {

    String API_ROUTE = ".";

    @GET(API_ROUTE)
    Call< List<ProspectosObj> > getProspectos();

}