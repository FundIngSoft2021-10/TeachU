package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Registro_Tutor extends AppCompatActivity {
    Spinner spiner_tipodoc,spiner_clases;
    EditText N_documento, descripcion;
    TextView salida;
    String Nusuario;
    String id, id_clase;
    Button btn_confirmarDatos,btn_confirmarRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__tutor);
        obtDatos();
        spiner_tipodoc = findViewById(R.id.spinner2_RegistroTutor);
        spiner_clases = findViewById(R.id.spinner_clases);
        N_documento = findViewById(R.id.ET_numcedula_RegistroTutor);
        descripcion = findViewById(R.id.ET_descripcion_RegistroTutor);
        btn_confirmarDatos = findViewById(R.id.btn_confirmardatos_registroTutor);
        btn_confirmarRegistro = findViewById(R.id.BTN_completar_registro_tutor);
        salida = findViewById(R.id.salida_tuto);
        btn_confirmarRegistro.setEnabled(false);
        Nusuario = getIntent().getExtras().getString("NombreU");


        String [] tipo_documento = {"CC","TI","CE"};

        ArrayAdapter<String> adaptador   = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipo_documento);
        spiner_tipodoc.setAdapter(adaptador);

        btn_confirmarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!N_documento.getText().toString().isEmpty() && !descripcion.getText().toString().isEmpty()) {
                    buscarId("https://webserviceteachu.000webhostapp.com/index.php/usuarios.php");
                    buscarIdClase("https://webserviceteachu.000webhostapp.com/index.php/Clases.php");
                    salida.setText(id);
                    btn_confirmarRegistro.setEnabled(true);
                    spiner_tipodoc.setEnabled(false);
                    spiner_clases.setEnabled(false);
                    N_documento.setEnabled(false);
                    descripcion.setEnabled(false);
                }else{
                    Toast.makeText(getApplicationContext(), "LLENE TODOS LOS CAMPOS PARA CONTINUAR", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_confirmarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarId("https://webserviceteachu.000webhostapp.com/index.php/usuarios.php");
                buscarIdClase("https://webserviceteachu.000webhostapp.com/index.php/Clases.php");
                salida.setText("Usuario: "+id+" Clase:"+id_clase);
                if(id.equals(null)){
                    Toast.makeText(getApplicationContext(), "Fallo en el registro intente mas tarde", Toast.LENGTH_SHORT).show();
                }else {
                    ingresarTutor("https://webserviceteachu.000webhostapp.com/index.php/Registro_Tutor.php");
                }
                if (id.equals(null) || id_clase.equals(null)){
                    Toast.makeText(getApplicationContext(), "Fallo en el registro intente mas tarde", Toast.LENGTH_SHORT).show();
                }else {
                    ingresarTutorXclase("https://webserviceteachu.000webhostapp.com/index.php/Registro_TutorxClase.php");
                }
                Intent int_login = new Intent(Registro_Tutor.this,Login.class);
                startActivity(int_login);
            }
        });
    }
    public void obtDatos() {
        AsyncHttpClient cliente = new AsyncHttpClient();


        String url = "https://webserviceteachu.000webhostapp.com/index.php/Clases.php";

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
        spiner_clases.setAdapter(adapter);
    }

    public ArrayList<String> obtDatosJason(String response) {
        ArrayList<String> listado = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i = 0; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString("Nclase");
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
                        if(aux.equals(Nusuario)){
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
    private void ingresarTutor(String URL){
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
                parametros.put("cedula",N_documento.getText().toString());
                parametros.put("tipodoc",spiner_tipodoc.getSelectedItem().toString());
                parametros.put("descripcion",descripcion.getText().toString());
                parametros.put("ranking","0");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void ingresarTutorXclase(String URL){
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
                parametros.put("id_clase",id_clase);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void buscarIdClase(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String aux = jsonObject.getString("Nclase");
                        if(aux.equals(spiner_clases.getSelectedItem().toString())){
                            id_clase = jsonObject.getString("idClase");
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
}