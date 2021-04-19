package activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class  Registro_Estudiante extends AppCompatActivity {
    Spinner listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__estudiante);
        listado = (Spinner) findViewById(R.id.spinnercarrera);
        obtDatos();
    }


    public void obtDatos() {
        AsyncHttpClient cliente = new AsyncHttpClient();
        String url = "http://192.168.0.28:80/webservices/prueba.php";
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
            for (int i = 1; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString("Ncarrera");
                listado.add(texto);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listado;
    }
}