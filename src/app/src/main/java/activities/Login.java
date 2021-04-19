package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
    EditText usuario;
    EditText password;
    ArrayList<String> usuarios = new ArrayList<String>();
    ArrayList<String> contrasenas = new ArrayList<String>();
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
                boolean ingresar = false;
                for(int i = 0; i < usuarios.size() ; i++){
                    if(usuario.getText().toString().equalsIgnoreCase(usuarios.get(i)) && password.getText().toString().equalsIgnoreCase(contrasenas.get(i))){
                        ingresar = true;
                    }
                }
                if(ingresar){
                    Intent home = new Intent(Login.this, Home.class);
                    startActivity(home);
                }else{
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
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public ArrayList<String> obtDatosJason(String response, String parametro ) {
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
}