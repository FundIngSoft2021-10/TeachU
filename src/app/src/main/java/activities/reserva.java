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

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class reserva extends AppCompatActivity {
    TextView tv,tv_tut,tv_precio;
    Button btn,btn_compra;
    String precio  = "30000.0";
    List<String> clases  = new ArrayList<>();
    List<String> id_clases  = new ArrayList<>();
    String idTutor = "",clase = "",idestu= "",fecha = "",idClase= "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        tv = findViewById(R.id.tvf);
        tv_tut = findViewById(R.id.tv_tut);
        tv_precio = findViewById(R.id.tv_precio);
        btn = findViewById(R.id.btn_fecha);
        btn_compra = findViewById(R.id.btn_compra);
        idTutor = getIntent().getExtras().getString("id_tut");
        clase = getIntent().getExtras().getString("clase");
        idestu = getIntent().getExtras().getString("id_est");
        clases.clear();
        id_clases.clear();
        obtDatos();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(reserva.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fecha = dayOfMonth + "/" + month + "/" + year;
                        tv.setText(fecha);
                    }
                },ano,mes,dia);
                dpd.show();
            }
        });
        btn_compra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<clases.size();i++){
                    if(clases.get(i).equals(clase)){
                        idClase = id_clases.get(i);
                    }
                }
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
                id_clases = obtDatosJason(new String(responseBody));
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
                texto = jsonArray.getJSONObject(i).getString("idClase");
                listado.add(texto);
                clases.add(jsonArray.getJSONObject(i).getString("Nclase").toUpperCase());
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
                parametros.put("IdEstudiante",idestu);
                parametros.put("IdTutor",idTutor);
                parametros.put("IdTutoria",cod);
                parametros.put("Fecha",fecha);
                parametros.put("Duracion","1");
                parametros.put("idClase",idClase);
                parametros.put("Precio",precio);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}