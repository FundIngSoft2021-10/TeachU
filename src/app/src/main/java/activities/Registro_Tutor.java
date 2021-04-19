package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Registro_Tutor extends AppCompatActivity {
    Spinner spiner_tipodoc,spiner_clases;
    EditText N_documento, descripcion;
    String Nusuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__tutor);
        obtDatos();
        spiner_tipodoc = findViewById(R.id.spinner2_RegistroTutor);
        spiner_clases = findViewById(R.id.spinner_clases);
        N_documento = findViewById(R.id.ET_numcedula_RegistroTutor);
        descripcion = findViewById(R.id.ET_descripcion_RegistroTutor);
        Nusuario = getIntent().getExtras().getString("NombreU");
        String [] tipo_documento = {"CC","TI","CE"};

        ArrayAdapter<String> adaptador   = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipo_documento);
        spiner_tipodoc.setAdapter(adaptador);

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
}