package activities;

import androidx.appcompat.app.AppCompatActivity;

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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class recuperacion_de_contrasena extends AppCompatActivity {
    EditText correo,nusuario,ncontrasena;
    Button btn_confirmar;
    TextView te;
    List<String> nombres = new ArrayList<>();
    List<String> correos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion_de_contrasena);
        nombres.clear();
        correos.clear();
        obtDatos();
        correo = findViewById(R.id.ed_correo_rec);
        nusuario = findViewById(R.id.EDRE_nusuario);
        ncontrasena = findViewById(R.id.EDRE_ncontrasena);
        btn_confirmar  = findViewById(R.id.btn_gc);
        te = findViewById(R.id.tvp);
        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te.setText(nombres.get(1));
                if(!correo.getText().toString().isEmpty() && !nusuario.getText().toString().isEmpty() && !ncontrasena.getText().toString().isEmpty()){
                    boolean estaN = false, estaC = false;

                    for(int i = 0; i < nombres.size(); i++ ){
                        if(nombres.get(i).equals(nusuario.getText().toString())){
                            estaN = true;
                            //te.setText(nombres.get(i));
                        }
                    }
                    for(int j = 0; j< correos.size(); j++){
                        if(correos.get(j).equals(correo.getText().toString())){
                            estaC = true;
                            //te.setText(correos.get(j));
                        }
                    }
                    if (estaC == true && estaN == true){
                        ejecutarservicio("https://webserviceteachu.000webhostapp.com/index.php/contraUsuario.php");
                    }else{
                        Toast.makeText(getApplicationContext(), "USUARIO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "LLENE TODOS LOS DATOS PARA CONTINUAR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void obtDatos() {
        AsyncHttpClient cliente = new AsyncHttpClient();


        String url = "https://webserviceteachu.000webhostapp.com/index.php/usuarios.php";

        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);

        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                nombres = obtDatosJason(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }



    public ArrayList<String> obtDatosJason(String response) {
        ArrayList<String> listado = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i = 0; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString("Nusuario");
                listado.add(texto);
                correos.add(jsonArray.getJSONObject(i).getString("CorreoInst"));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listado;
    }
    private void ejecutarservicio(String URL){
        //setContentView(R.layout.activity_registro);
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
                String cod = "3";
                parametros.put("nUsuario",ncontrasena.getText().toString());
                parametros.put("contrasena", nusuario.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}