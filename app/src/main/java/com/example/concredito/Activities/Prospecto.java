package com.example.concredito.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.concredito.Apis.ProspectoApi;
import com.example.concredito.Objetos.DocumentoObj;
import com.example.concredito.Objetos.ProspectoObj;
import com.example.concredito.R;
import com.example.concredito.RecyclerViews.RecyclerViewDocumentos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Prospecto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospecto);

        String prospecto = getIntent().getStringExtra("prospecto");

        obtenerProspecto(prospecto);

    }

    private void obtenerProspecto(String prospecto)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prospectoscc.herokuapp.com/prospectos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProspectoApi postService = retrofit.create(ProspectoApi.class);
        Call<ProspectoObj> call = postService.getProspecto(prospecto);

        call.enqueue(new Callback<ProspectoObj>() {
            @Override
            public void onResponse(Call<ProspectoObj> call, Response<ProspectoObj> response) {

                ProspectoObj prosp = response.body();
                llenarDatos(prosp);
            }

            @Override
            public void onFailure(Call<ProspectoObj> call, Throwable t) {
            }
        });
    }

    private void llenarDatos(ProspectoObj prosp)
    {
        TextView textNombre = findViewById(R.id.prospectoNombre);
        TextView textApellidoP = findViewById(R.id.prospectoApellidoP);
        TextView textApellidoM = findViewById(R.id.prospectoApellidoM);
        TextView textCalle = findViewById(R.id.prospectoCalle);
        TextView textNumero = findViewById(R.id.prospectoNumero);
        TextView textColonia = findViewById(R.id.prospectoColonia);
        TextView textCodigoPostal = findViewById(R.id.prospectoCodigoPostal);
        TextView textTelefono = findViewById(R.id.prospectoTelefono);
        TextView textRFC = findViewById(R.id.prospectoRFC);
        TextView textRechazo = findViewById(R.id.prospectoRechazo);

        textNombre.setText(prosp.getNombre());
        textApellidoP.setText(prosp.getApellidoP());
        textApellidoM.setText(prosp.getApellidoM());
        textCalle.setText(prosp.getCalle());
        textNumero.setText(String.valueOf(prosp.getNumero()));
        textColonia.setText(prosp.getColonia());
        textCodigoPostal.setText(String.valueOf(prosp.getCodigoPostal()));
        textTelefono.setText(String.valueOf(prosp.getTelefono()));
        textRFC.setText(prosp.getRFC());

        if(prosp.getEstatus() == 2)
        {
            textRechazo.setVisibility(View.VISIBLE);
            textRechazo.setText(prosp.getObservacionRechazo());
        }

        desplegarDocumentos(prosp.getDocumentos());

    }

    private void desplegarDocumentos(ArrayList<DocumentoObj> listaDocumentos)
    {
        RecyclerViewDocumentos adapter = new RecyclerViewDocumentos(listaDocumentos);
        RecyclerView recyclerView = findViewById(R.id.prospectoDocumentos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
}