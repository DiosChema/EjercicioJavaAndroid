package com.example.concredito.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.concredito.Apis.ProspectosApi;
import com.example.concredito.Objetos.ProspectosObj;
import com.example.concredito.R;
import com.example.concredito.RecyclerViews.RecyclerViewProspectos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignarFuncionBotones();
        obtenerProspectos();
    }

    private void asignarFuncionBotones()
    {
        ImageButton altaProspecto = (ImageButton) findViewById(R.id.altaProspecto);
        altaProspecto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), DarAltaProspecto.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    private void obtenerProspectos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prospectoscc.herokuapp.com/prospectos/prospectos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProspectosApi postService = retrofit.create(ProspectosApi.class);
        Call< List<ProspectosObj> > call = postService.getProspectos();

        call.enqueue(new Callback<List<ProspectosObj>>() {
            @Override
            public void onResponse(Call<List<ProspectosObj>> call, Response<List<ProspectosObj>> response) {
                ArrayList<ProspectosObj> prospectos = new ArrayList<>();

                for(ProspectosObj prosp : response.body()) {
                    prospectos.add(prosp);
                }

                desplegarProspectos(prospectos);
            }

            @Override
            public void onFailure(Call<List<ProspectosObj>> call, Throwable t) {
            }
        });
    }

    private void desplegarProspectos(ArrayList<ProspectosObj> prospectos)
    {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewProspectos);
        RecyclerViewProspectos adapter = new RecyclerViewProspectos(prospectos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        obtenerProspectos();
        super.onResume();

    }

}