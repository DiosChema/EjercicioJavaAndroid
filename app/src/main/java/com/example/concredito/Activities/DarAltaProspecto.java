package com.example.concredito.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.concredito.Apis.ProspectoApi;
import com.example.concredito.Objetos.DocumentoObj;
import com.example.concredito.Objetos.ProspectoObj;
import com.example.concredito.R;
import com.example.concredito.RecyclerViews.RecyclerViewDocumentos;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DarAltaProspecto extends AppCompatActivity {

    ArrayList<DocumentoObj> listaDocumentos = new ArrayList<>();
    RecyclerViewDocumentos adapter = new RecyclerViewDocumentos(listaDocumentos);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_alta_prospecto);

        asignarFuncionBotones();
        desplegarDocumentos();

    }

    private void asignarFuncionBotones()
    {
        ImageButton regresarBoton = findViewById(R.id.darAltaProspectoRegresar);
        regresarBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        ImageButton darAltaBoton = findViewById(R.id.darAltaProspectoAnadir);
        darAltaBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                validarDatos();
            }
        });

        ImageButton buscarDocumento = findViewById(R.id.darAltaProspectoBuscarDocumento);
        buscarDocumento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showFileChooser();
            }
        });


    }

    private void validarDatos()
    {
        TextView textNombre = findViewById(R.id.darAltaProspectoNombre);
        TextView textApellidoP = findViewById(R.id.darAltaProspectoApellidoP);
        TextView textApellidoM = findViewById(R.id.darAltaProspectoApellidoM);
        TextView textCalle = findViewById(R.id.darAltaProspectoCalle);
        TextView textNumero = findViewById(R.id.darAltaProspectoNumero);
        TextView textColonia = findViewById(R.id.darAltaProspectoColonia);
        TextView textCodigoPostal = findViewById(R.id.darAltaProspectoCodigoPostal);
        TextView textTelefono = findViewById(R.id.darAltaProspectoTelefono);
        TextView textRFC = findViewById(R.id.darAltaProspectoRFC);

        if(textNombre.length() <= 3)
        {
            Toast.makeText(this,getString(R.string.darAltaProspectoNombre),Toast.LENGTH_SHORT).show();
            return;
        }

        if(textApellidoP.length() <= 3)
        {
            Toast.makeText(this,getString(R.string.darAltaProspectoApellido),Toast.LENGTH_SHORT).show();
            return;
        }

        if(textCalle.length() <= 3)
        {
            Toast.makeText(this,getString(R.string.darAltaProspectoCalle),Toast.LENGTH_SHORT).show();
            return;
        }

        if(textNumero.length() <= 3)
        {
            Toast.makeText(this,getString(R.string.darAltaProspectoNumero),Toast.LENGTH_SHORT).show();
            return;
        }

        if(textColonia.length() <= 3)
        {
            Toast.makeText(this,getString(R.string.darAltaProspectoColonia),Toast.LENGTH_SHORT).show();
            return;
        }

        if(textCodigoPostal.length() <= 3)
        {
            Toast.makeText(this,getString(R.string.darAltaProspectoCodigoPostal),Toast.LENGTH_SHORT).show();
            return;
        }

        if(textTelefono.length() <= 6)
        {
            Toast.makeText(this,getString(R.string.darAltaProspectotelefono),Toast.LENGTH_SHORT).show();
            return;
        }

        if(textRFC.length() <= 11)
        {
            Toast.makeText(this,getString(R.string.darAltaProspectoRFC),Toast.LENGTH_SHORT).show();
            return;
        }

        ProspectoObj prospecto = new ProspectoObj(
                0,
                Integer.parseInt(textCodigoPostal.getText().toString()),
                Integer.parseInt(textNumero.getText().toString()),
                Long.parseLong(textTelefono.getText().toString()),
                (short) 0,
                textNombre.getText().toString(),
                textApellidoP.getText().toString(),
                textApellidoM.getText().toString(),
                textCalle.getText().toString(),
                textColonia.getText().toString(),
                textRFC.getText().toString(),
                "",
                listaDocumentos
        );

        altaProspecto(prospecto);

    }

    private void altaProspecto(ProspectoObj prospecto)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prospectoscc.herokuapp.com/prospectos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProspectoApi postService = retrofit.create(ProspectoApi.class);
        Call<ProspectoObj> call = postService.postProspecto(prospecto);

        call.enqueue(new Callback<ProspectoObj>() {
            @Override
            public void onResponse(Call<ProspectoObj> call, Response<ProspectoObj> response) {
                finish();
            }

            @Override
            public void onFailure(Call<ProspectoObj> call, Throwable t) {
            }
        });
    }

    private void desplegarDocumentos()
    {
        RecyclerView recyclerView = findViewById(R.id.darAltaProspectoDocumentos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED)
            {
                //show popup to request runtime permission
                String[] permisos = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permisos, 1000);
            }
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, this.getString(R.string.darAltaDocumentoSeleccionar)),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, this.getString(R.string.darAltaDocumentoError),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    File file = new File(uri.getPath());

                    listaDocumentos.add(new DocumentoObj(file.getName(), uri.getPath()));
                    adapter.notifyDataSetChanged();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}