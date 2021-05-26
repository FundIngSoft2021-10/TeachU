package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class Perfil_Tutor extends AppCompatActivity {
    TextView nombre,descripcion,asignatura;
    Button btn_reserva,rec;
    List<String> id_usuario = new ArrayList<>();
    List<String> id_tutor = new ArrayList<>();
    List<String> nombreU = new ArrayList<>();
    List<String> descript = new ArrayList<>();
    List<String> rank = new ArrayList<>();
    String id = "",des = "",ran = "";
    String as = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_tutor);
        id_usuario.clear();
        id_tutor.clear();
        nombreU.clear();
        descript.clear();
        rank.clear();
        obtDatos();
        obtDatosTutor();

        nombre = findViewById(R.id.Tv_nombre);
        descripcion = findViewById(R.id.Tv_descrip);
        asignatura = findViewById(R.id.Tv_asignatura);
        btn_reserva = findViewById(R.id.btn_res);
        rec = findViewById(R.id.rec);
        String nu = getIntent().getExtras().getString("Nombre");
        String as = getIntent().getExtras().getString("Asignatura");
        nombre.setText(nu);
        asignatura.setText(as);
        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<nombreU.size();i++){
                    if(nombreU.get(i).equals(nu)){
                        id = id_usuario.get(i);
                    }
                }
                for(int j = 0; j<id_tutor.size();j++){
                    if(id_tutor.get(j).equals(id)){
                        des = descript.get(j);
                        ran = rank.get(j);
                    }
                }
                descripcion.setText(des);
            }
        });
        btn_reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reserva = new Intent(Perfil_Tutor.this,reserva.class);
                reserva.putExtra("clase",as);
                startActivity(reserva);
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
                id_usuario = obtDatosJason(new String(responseBody));
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
                texto = jsonArray.getJSONObject(i).getString("Id_Usuario");
                listado.add(texto);
                nombreU.add(jsonArray.getJSONObject(i).getString("Nombre").toUpperCase());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listado;
    }
    public void obtDatosTutor() {
        AsyncHttpClient cliente = new AsyncHttpClient();


        String url = "https://webserviceteachu.000webhostapp.com/index.php/buscarTutor.php";

        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);

        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                id_tutor = obtDatosJasonTutor(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }



    public ArrayList<String> obtDatosJasonTutor(String response) {
        ArrayList<String> listado = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i = 0; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString("Id_Usuario");
                listado.add(texto);
                descript.add(jsonArray.getJSONObject(i).getString("Descripcion").toUpperCase());
                rank.add(jsonArray.getJSONObject(i).getString("Ranking"));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listado;
    }
}