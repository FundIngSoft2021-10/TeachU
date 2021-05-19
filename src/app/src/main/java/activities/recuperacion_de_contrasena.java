package activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class recuperacion_de_contrasena extends AppCompatActivity {
    EditText correo,nusuario,ncontrasena;
    Button btn_confirmar;
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
                texto = jsonArray.getJSONObject(i).getString("Nusuario").toUpperCase();
                listado.add(texto);
                correos.add(jsonArray.getJSONObject(i).getString("CorreoInst").toUpperCase());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listado;
    }
}