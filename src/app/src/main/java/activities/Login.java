package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
    EditText usuario;
    EditText password;
    String id;
    ArrayList<String> usuarios = new ArrayList<String>();
    ArrayList<String> contrasenas = new ArrayList<String>();
    ArrayList<String> ids = new ArrayList<String>();
    boolean usuarioHome ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        obtDatos();
        usuario = findViewById(R.id.ET_Nusuario);
        password = findViewById(R.id.TextPassword);

        Button btn_registrarse = findViewById(R.id.BTN_registro);
        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(Login.this, Registro.class);
                startActivity(registro);
            }
        });

        Button btn_ingresar = findViewById(R.id.BTN_ingreso);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioHome = false;
                boolean ingresar = false;
                for (int i = 0; i < usuarios.size(); i++) {
                    if (usuario.getText().toString().equalsIgnoreCase(usuarios.get(i)) && password.getText().toString().equalsIgnoreCase(contrasenas.get(i))) {
                        id = ids.get(i).toString();
                        ingresar = true;
                    }
                }
                if (ingresar) {

                    buscarId("https://webserviceteachu.000webhostapp.com/index.php/buscarTutor.php");
                    TimerTask tarea = new TimerTask(){
                        @Override
                        public void run() {
                            if(usuarioHome){
                                Intent homeTutor = new Intent(Login.this, HomeTutor.class);
                                homeTutor.putExtra("Usuario", usuario.getText().toString());
                                startActivity(homeTutor);
                            }else{
                                Intent home = new Intent(Login.this, Home.class);
                                home.putExtra("Usuario", usuario.getText().toString());
                                startActivity(home);
                            }
                        }
                    };
                    Timer tiempoo = new Timer();
                    tiempoo.schedule(tarea, 1000);



                } else {
                    Toast.makeText(getApplicationContext(), "USUARIO O CONTRASENA INCORRECTA", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void obtDatos() {
        final AsyncHttpClient[] cliente = {new AsyncHttpClient()};
        String url = "https://webserviceteachu.000webhostapp.com/index.php/usuarios.php";
        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);
        cliente[0].post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                usuarios = obtDatosJason(new String(responseBody), "Nusuario");
                contrasenas = obtDatosJason(new String(responseBody), "Contrasena");
                ids = obtDatosJason(new String(responseBody), "Id_Usuario");
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

    private void buscarId(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String aux = jsonObject.getString("Id_Usuario");
                        if(aux.equals(id)){
                            usuarioHome = true;
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
