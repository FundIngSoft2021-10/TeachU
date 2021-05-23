package activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class Buscar extends Fragment {
    Spinner spiner_Filtro;
    EditText busqueda;
    Button buscar;
    ListView salida;
    List<String> tutores = new ArrayList<>();
    List<String> clases = new ArrayList<>();
    List<String> resultado =new ArrayList<>();
    public Buscar() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tutores.clear();
        clases.clear();
        resultado.clear();
        obtDatos();
        busqueda = view.findViewById(R.id.Texto_De_Busqueda);
        spiner_Filtro = view.findViewById(R.id.Filtro_Busqueda);
        buscar = view.findViewById(R.id.Bt_Buscar);
        salida = view.findViewById(R.id.lista_resultados);
        String [] tipo_Busqueda = {"Tutor","Clases"};

        ArrayAdapter<String> adaptador   = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, tipo_Busqueda);
        spiner_Filtro.setAdapter(adaptador);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.clear();
                salida.setAdapter(null);
                if(spiner_Filtro.getSelectedItem().toString().equals("Clases")) {
                    String nclase = busqueda.getText().toString().toUpperCase();
                    for (int i = 0; i < clases.size(); i++) {
                        if (clases.get(i).contains(nclase)) {
                            resultado.add("Tutor: " + tutores.get(i) + " " + "Asignarura: " + clases.get(i));
                        }
                    }
                }
                if(spiner_Filtro.getSelectedItem().toString().equals("Tutor")) {
                    String ntutor = busqueda.getText().toString().toUpperCase();
                    for (int i = 0; i < tutores.size(); i++) {
                        if (tutores.get(i).contains(ntutor)) {
                            resultado.add("Tutor: " + tutores.get(i) + " " + "Asignarura: " + clases.get(i));
                        }
                    }
                }
                ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, resultado);
                salida.setAdapter(adaptador2);
            }
        });
        salida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });


    }
    public void obtDatos() {
        AsyncHttpClient cliente = new AsyncHttpClient();


        String url = "https://webserviceteachu.000webhostapp.com/index.php/TutorXClaseCompleto.php";

        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);

        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               tutores = obtDatosJason(new String(responseBody));
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
                texto = jsonArray.getJSONObject(i).getString("Nombre").toUpperCase();
                listado.add(texto);
                clases.add(jsonArray.getJSONObject(i).getString("Nclase").toUpperCase());
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return listado;
    }
}