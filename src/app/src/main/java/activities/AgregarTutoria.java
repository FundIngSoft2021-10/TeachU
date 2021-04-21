package activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;
import entities.Carrera;
import entities.Clase;


public class AgregarTutoria extends Fragment {

    Spinner listadoCar, listadoCla;
    ArrayList<String> idCarreras = new ArrayList<String>();
    ArrayList<String> nCarreras = new ArrayList<String>();
    ArrayList<String> Nclases = new ArrayList<String>();
    ArrayList<String> idlases = new ArrayList<String>();
    ArrayList<String> Carclases = new ArrayList<String>();
    ArrayList<String> Clases = new ArrayList<String>();
    ArrayList<String> usuarios = new ArrayList<String>();
    ArrayList<String> ips = new ArrayList<String>();
    Button SelecCar, SelecClas;
    private String tutor, idTutor, idClase;


        public String getTutor() {
        return tutor;
    }

    public String getIdClase() {
        return idClase;
    }

    public void setIdClase(String idClase) {
        this.idClase = idClase;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
    }
    public AgregarTutoria() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_tutoria, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button cerrarSesion = view.findViewById(R.id.button_cerrarSesion_Agregar);
        Button guardarClases = view.findViewById(R.id.button_Guardar_Clase_Agregar);
        listadoCar = view.findViewById(R.id.spinnerCarrera_Agregar);
        listadoCla = view.findViewById(R.id.spinnerClase_Agregar);
        SelecCar = view.findViewById(R.id.button_SelecCar_Agregar);
        SelecClas = view.findViewById(R.id.button_SeleClas_Agregar);
        this.obtDatos();
        this.obtDatosCla();
        this.obtDatosUsuarios();
        BuscarId(getTutor());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, nCarreras);
        listadoCar.setAdapter(adapter);
        SelecCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Clases.clear();
                for(int i = 0; i < nCarreras.size(); i++){
                    if(nCarreras.get(i).equalsIgnoreCase(listadoCar.getSelectedItem().toString())){
                          for(int j = 0; j < idlases.size(); j++){
                              if(Carclases.get(j).equalsIgnoreCase(idCarreras.get(i))){
                                  Clases.add(Nclases.get(j));
                              }
                          }
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Clases);
                listadoCla.setAdapter(adapter);
                listadoCla.setEnabled(true);
            }
        });
        SelecClas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listadoCla.setEnabled(false);
                for (int i = 0; i < Nclases.size(); i++){
                    if(listadoCla.getSelectedItem().toString().equalsIgnoreCase(Nclases.get(i))){
                        setIdClase(idlases.get(i));
                    }
                }
            }
        });
        guardarClases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarClase("https://webserviceteachu.000webhostapp.com/index.php/Registro_TutorxClase.php");
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getContext(), Login.class);
                startActivity(login);
            }
        });
       }



    public void obtDatos() {
        AsyncHttpClient cliente = new AsyncHttpClient();


        String url = "https://webserviceteachu.000webhostapp.com/index.php/prueba.php";

        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);

        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                idCarreras = obtDatosJason(new String(responseBody), "IdCarrera");
                nCarreras = obtDatosJason(new String(responseBody), "Ncarrera");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    public void obtDatosCla() {
        final AsyncHttpClient[] cliente = {new AsyncHttpClient()};
        String url = "https://webserviceteachu.000webhostapp.com/index.php/Clases.php";
        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);
        cliente[0].post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Carclases = obtDatosJason(new String(responseBody), "IdCarrera");
                Nclases = obtDatosJason(new String(responseBody), "Nclase");
                idlases = obtDatosJason(new String(responseBody), "idClase");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public ArrayList<String> obtDatosJason(String response, String parametro) {
        ArrayList<String> listado = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i = 0; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString(parametro);
                listado.add(texto);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listado;
    }

    public void obtDatosUsuarios() {
        final AsyncHttpClient[] cliente = {new AsyncHttpClient()};
        String url = "https://webserviceteachu.000webhostapp.com/index.php/usuarios.php";
        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);
        cliente[0].post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ips = obtDatosJason(new String(responseBody), "Id_Usuario");
                usuarios = obtDatosJason(new String(responseBody), "Nusuario");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void insertarClase(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext().getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext().getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id_usuario",getIdTutor());
                parametros.put("id_clase",getIdClase());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    public void BuscarId (String Usuario){
        for (int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).equalsIgnoreCase(Usuario)){
                setIdTutor(ips.get(i));
            }
        }
    }

}