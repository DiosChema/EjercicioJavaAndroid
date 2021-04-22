package com.example.concredito.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.concredito.Apis.ProspectoApi;
import com.example.concredito.Objetos.ProspectoObj;
import com.example.concredito.Objetos.StatusObj;
import com.example.concredito.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActualizarProspecto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_prospecto);

        String prospecto = getIntent().getStringExtra("prospecto");

        asignarFuncionBotones(prospecto);
        obtenerProspecto(prospecto);
    }

    private void asignarFuncionBotones(final String prospecto)
    {
        Button autorizarBoton = (Button) findViewById(R.id.actualizarProspectoAutorizar);
        autorizarBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                actualizarProspecto(new StatusObj(Integer.parseInt(prospecto), (short)1, ""));
            }
        });

        Button rechazarBoton = (Button) findViewById(R.id.actualizarProspectoRechazar);
        rechazarBoton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onButtonShowPopupWindowClick(view, prospecto);
            }
        });
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
        TextView textNombre = (TextView) findViewById(R.id.actualizarProspectoNombre);
        TextView textApellidoP = (TextView) findViewById(R.id.actualizarProspectoApellidoP);
        TextView textApellidoM = (TextView) findViewById(R.id.actualizarProspectoApellidoM);
        TextView textCalle = (TextView) findViewById(R.id.actualizarProspectoCalle);
        TextView textNumero = (TextView) findViewById(R.id.actualizarProspectoNumero);
        TextView textColonia = (TextView) findViewById(R.id.actualizarProspectoColonia);
        TextView textCodigoPostal = (TextView) findViewById(R.id.actualizarProspectoCodigoPostal);
        TextView textTelefono = (TextView) findViewById(R.id.actualizarProspectoTelefono);
        TextView textRFC = (TextView) findViewById(R.id.actualizarProspectoRFC);

        textNombre.setText(prosp.getNombre());
        textApellidoP.setText(prosp.getApellidoP());
        textApellidoM.setText(prosp.getApellidoM());
        textCalle.setText(prosp.getCalle());
        textNumero.setText(String.valueOf(prosp.getNumero()));
        textColonia.setText(prosp.getColonia());
        textCodigoPostal.setText(String.valueOf(prosp.getCodigoPostal()));
        textTelefono.setText(String.valueOf(prosp.getTelefono()));
        textRFC.setText(prosp.getRFC());

    }

    private void actualizarProspecto(StatusObj prospecto)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://prospectoscc.herokuapp.com/prospectos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProspectoApi postService = retrofit.create(ProspectoApi.class);
        Call<StatusObj> call = postService.putProspecto(prospecto);

        call.enqueue(new Callback<StatusObj>() {
            @Override
            public void onResponse(Call<StatusObj> call, Response<StatusObj> response) {
                finish();
            }

            @Override
            public void onFailure(Call<StatusObj> call, Throwable t) {
            }
        });
    }

    public void onButtonShowPopupWindowClick(View view, final String prospecto) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.observacion, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        final TextView observacionText = popupView.findViewById(R.id.observacionText);
        Button observacionConfirmar = popupView.findViewById(R.id.observacionConfirmar);
        Button observacionCancelar = popupView.findViewById(R.id.observacionCancelar);

        observacionConfirmar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(observacionText.getText().toString().length() > 1)
                {
                    actualizarProspecto(new StatusObj(Integer.parseInt(prospecto), (short)2, observacionText.getText().toString()));
                }

            }
        });

        observacionCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                popupWindow.dismiss();
            }
        });




        // dismiss the popup window when touched

    }
}