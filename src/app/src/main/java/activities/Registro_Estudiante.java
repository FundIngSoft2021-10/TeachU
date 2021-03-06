package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class  Registro_Estudiante extends AppCompatActivity {
    Spinner listado;
    String id;
    String id_usuario;
    String id_carrera;
    TextView salida;
    Button btn_terminarRegistro;
    Button btn_seleccionar_carrera;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__estudiante);
        id_usuario = getIntent().getExtras().getString("NombreU");
        //buscarId("https://webserviceteachu.000webhostapp.com/index.php/usuarios.php");
        listado = (Spinner) findViewById(R.id.spinnercarrera);
        obtDatos();
        id_usuario = getIntent().getExtras().getString("NombreU");
        salida = findViewById(R.id.salida);
        btn_terminarRegistro = findViewById(R.id.BTN_c_registro);
        btn_seleccionar_carrera = findViewById(R.id.BTN_seleccionar_carrera);
        btn_terminarRegistro.setEnabled(false);

        btn_seleccionar_carrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarId("https://webserviceteachu.000webhostapp.com/index.php/usuarios.php");
                buscarIdCarrera("https://webserviceteachu.000webhostapp.com/index.php/prueba.php");
                salida.setText("Usuario:"+id+" "+"carrera:"+id_carrera);
                listado.setEnabled(false);
                btn_terminarRegistro.setEnabled(true);
            }
        });
        btn_terminarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscarId("https://webserviceteachu.000webhostapp.com/index.php/usuarios.php");
                buscarIdCarrera("https://webserviceteachu.000webhostapp.com/index.php/prueba.php");
                salida.setText("Usuario:"+id+" "+"carrera:"+id_carrera);
                insertarEstudiante("https://webserviceteachu.000webhostapp.com/index.php/Registro_estudiantes.php");
                Intent int_login = new Intent(Registro_Estudiante.this, Login.class);
                startActivity(int_login);


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
                CargaLista(obtDatosJason(new String(responseBody)));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void CargaLista(ArrayList<String> datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);
        listado.setAdapter(adapter);
    }

    public ArrayList<String> obtDatosJason(String response) {
        ArrayList<String> listado = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i = 0; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString("Ncarrera");
                listado.add(texto);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listado;
    }

    private void buscarId(String URL){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String aux = jsonObject.getString("Nusuario");
                        if(aux.equals(id_usuario)){
                            id = jsonObject.getString("Id_Usuario");
                        }


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    private void buscarIdCarrera(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String aux = jsonObject.getString("Ncarrera");
                        if(aux.equals(listado.getSelectedItem().toString())){
                            id_carrera = jsonObject.getString("IdCarrera");
                        }


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    private void insertarEstudiante(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id_usuario",id);
                parametros.put("id_Carrera",id_carrera);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


