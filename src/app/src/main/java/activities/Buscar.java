package activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;
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
    Spinner spiner_Filtro, calificar;
    EditText busqueda;
    Button buscar, misTuto;
    ListView salida, tutoriaClase;
    TextView tutor, fecha, nclase;
    private String estu;
    List<String> tutores = new ArrayList<>();
    List<String> clases = new ArrayList<>();
    List<String> resultado =new ArrayList<>();
    List<String> Rt = new ArrayList<>();
    List<String> Ra = new ArrayList<>();
    AlertDialog mDialog, mDialogMT;
    ArrayList<String> tutoriaxNomxC = new ArrayList<String>();
    ArrayList<String> tutoriaxNomTutoxC = new ArrayList<String>();
    ArrayList<String> tutoriaxFechxC = new ArrayList<String>();
    ArrayList<String> tutoriaxNoClasexC = new ArrayList<String>();
    ArrayList<String> tutoriaxidTuToriaxC = new ArrayList<String>();
    ArrayList<String> listaTextxTutoClase = new ArrayList<String>();
    String estud;
    public Buscar() {
        // Required empty public constructor
    }

    public Spinner getSpiner_Filtro() {
        return spiner_Filtro;
    }

    public void setSpiner_Filtro(Spinner spiner_Filtro) {
        this.spiner_Filtro = spiner_Filtro;
    }

    public EditText getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(EditText busqueda) {
        this.busqueda = busqueda;
    }

    public Button getBuscar() {
        return buscar;
    }

    public void setBuscar(Button buscar) {
        this.buscar = buscar;
    }

    public ListView getSalida() {
        return salida;
    }

    public void setSalida(ListView salida) {
        this.salida = salida;
    }

    public String getEstu() {
        return estu;
    }

    public void setEstu(String estu) {
        this.estu = estu;
    }

    public List<String> getTutores() {
        return tutores;
    }

    public void setTutores(List<String> tutores) {
        this.tutores = tutores;
    }

    public List<String> getClases() {
        return clases;
    }

    public void setClases(List<String> clases) {
        this.clases = clases;
    }

    public List<String> getResultado() {
        return resultado;
    }

    public void setResultado(List<String> resultado) {
        this.resultado = resultado;
    }

    public List<String> getRt() {
        return Rt;
    }

    public void setRt(List<String> rt) {
        Rt = rt;
    }

    public List<String> getRa() {
        return Ra;
    }

    public void setRa(List<String> ra) {
        Ra = ra;
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
        obtDatostutoriaxNomxC();
        tutores.clear();
        clases.clear();
        resultado.clear();
        Rt.clear();
        Ra.clear();
        obtDatos();
        AlertDialog.Builder alertDialogo = new AlertDialog.Builder(this.getContext());
        View row = getLayoutInflater().inflate(R.layout.mitutoriaxclase_popup, null);
        AlertDialog.Builder alertDialogo1 = new AlertDialog.Builder(this.getContext());
        View rows = getLayoutInflater().inflate(R.layout.calificar_tutoria, null);
        tutoriaClase = (ListView) row.findViewById(R.id.listaTutoriaPorClase);
        busqueda = view.findViewById(R.id.Texto_De_Busqueda);
        spiner_Filtro = view.findViewById(R.id.Filtro_Busqueda);
        buscar = view.findViewById(R.id.Bt_Buscar);
        salida = view.findViewById(R.id.lista_resultados);
        misTuto = view.findViewById(R.id.button_Tutorias_Estu);
        tutor = (TextView) rows.findViewById(R.id.text_Tutor_CT);
        fecha = (TextView) rows.findViewById(R.id.text_Fecha_CT);
        nclase = (TextView) rows.findViewById(R.id.text_Clase_CT);
        calificar = (Spinner) rows.findViewById(R.id.spinner_calificacion);
        String [] tipo_Busqueda = {"Tutor","Clases"};
        estud = this.getEstu();
        ArrayAdapter<String> adaptador   = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, tipo_Busqueda);
        spiner_Filtro.setAdapter(adaptador);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.clear();
                Rt.clear();
                Ra.clear();
                salida.setAdapter(null);
                if(spiner_Filtro.getSelectedItem().toString().equals("Clases")) {
                    String nclase = busqueda.getText().toString().toUpperCase();
                    for (int i = 0; i < clases.size(); i++) {
                        if (clases.get(i).contains(nclase)) {
                            resultado.add("Tutor: " + tutores.get(i) + " " + "Asignarura: " + clases.get(i));
                            Rt.add(tutores.get(i));
                            Ra.add(clases.get(i));

                        }
                    }
                }
                if(spiner_Filtro.getSelectedItem().toString().equals("Tutor")) {
                    String ntutor = busqueda.getText().toString().toUpperCase();
                    for (int i = 0; i < tutores.size(); i++) {
                        if (tutores.get(i).contains(ntutor)) {
                            resultado.add("Tutor: " + tutores.get(i) + " " + "Asignarura: " + clases.get(i));
                            Rt.add(tutores.get(i));
                            Ra.add(clases.get(i));
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
                //Toast.makeText(getContext(), Rt.get(position) + Ra.get(position), Toast.LENGTH_SHORT).show();
                Intent perfil = new Intent(getContext(),Perfil_Tutor.class);
                perfil.putExtra("Nombre",Rt.get(position));
                perfil.putExtra("Asignatura",Ra.get(position));
                perfil.putExtra("estu",estud);
                startActivity(perfil);
            }
        });

        misTuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaTextxTutoClase.clear();
                for(int i = 0; i < tutoriaxNomxC.size(); i++){
                    if(getEstu().equalsIgnoreCase(tutoriaxNomxC.get(i))){
                        String texto = " ID : "+ tutoriaxidTuToriaxC.get(i) + " Nombre: " + tutoriaxNomTutoxC.get(i) + " Fecha: " + tutoriaxFechxC.get(i);
                        listaTextxTutoClase.add(texto);
                    }
                }
                if(!listaTextxTutoClase.isEmpty()){
                    ArrayAdapter<String> adapterTutorias = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaTextxTutoClase);
                    tutoriaClase.setAdapter(adapterTutorias);
                    adapterTutorias.notifyDataSetChanged();
                    if(row.getParent() != null){
                        ((ViewGroup)row.getParent()).removeView(row);
                    }
                    alertDialogo.setView(row);
                    mDialog = alertDialogo.create();
                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDialog.show();
                }
                else{
                    Toast.makeText(getContext().getApplicationContext(), "NO TIENE TUTORIAS PARA ESTA CLASE", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tutoriaClase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i < tutoriaxNomTutoxC.size(); i++){
                    for(int j = 0; j < listaTextxTutoClase.size(); j++){
                        if(listaTextxTutoClase.get(j).contains(tutoriaxNomTutoxC.get(i)) && listaTextxTutoClase.get(j).contains(tutoriaxFechxC.get(i)) && listaTextxTutoClase.get(j).contains(tutoriaxidTuToriaxC.get(i))){
                            tutor.setText(tutoriaxNomTutoxC.get(i));
                            fecha.setText(tutoriaxFechxC.get(i));
                            nclase.setText(tutoriaxNoClasexC.get(i));
                            break;
                        }
                    }
                }

                String [] calificaciones = {"1","2","3","4","5"};
                ArrayAdapter<String> adapterCalifi = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, calificaciones);
                calificar.setAdapter(adapterCalifi);
                mDialog.dismiss();
                if(rows.getParent() != null){
                    ((ViewGroup)rows.getParent()).removeView(rows);
                }
                alertDialogo1.setView(rows);
                mDialogMT = alertDialogo1.create();
                mDialogMT.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialogMT.show();
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
    public void obtDatostutoriaxNomxC() {
        AsyncHttpClient cliente = new AsyncHttpClient();
        String url = "https://webserviceteachu.000webhostapp.com/index.php/TutoriasXUsuarios.php";
        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);
        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                tutoriaxNomxC = obtDatosJason(new String(responseBody), "NomEstudiante");
                tutoriaxNomTutoxC = obtDatosJason(new String(responseBody), "NomTutor");
                tutoriaxFechxC = obtDatosJason(new String(responseBody), "Fecha");
                tutoriaxNoClasexC = obtDatosJason(new String(responseBody), "Nclase");
                tutoriaxidTuToriaxC = obtDatosJason(new String(responseBody), "IdTutoria");
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

}