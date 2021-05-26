package activities;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import entities.Tutor;
import entities.Tutoria;

public class MisTutorias extends Fragment {
    HashMap<String, String> dia = new HashMap();
    HashMap<String, String> diaContrario = new HashMap();
    ArrayList<String> hora = new ArrayList<>();
    ArrayList<String> diaNcompleto = new ArrayList<>();
    ArrayList<String> iDDisp = new ArrayList<>();
    ArrayList<String> diaTable = new ArrayList<>();
    ArrayList<String> horaTable = new ArrayList<>();
    ArrayList<String> usuarios = new ArrayList<String>();
    ArrayList<String> ips = new ArrayList<String>();
    ArrayList<String> TextoLisView = new ArrayList<>();
    ArrayList<String> TextoMisClases = new ArrayList<>();
    Spinner ListaDia;
    Spinner ListaHora;
    AlertDialog mDialog, mDialogMT;
    TextView cliente, precio, fecha, nclase;
    ListView listaMiDisponibilidad, lxMxCalses, tutoriaClase;
    ArrayList<String> DxTxId = new ArrayList<String>();
    ArrayList<String> DxTxdia = new ArrayList<String>();
    ArrayList<String> DxTxhora = new ArrayList<String>();
    ArrayList<String> CxTxid = new ArrayList<String>();
    ArrayList<String> CxTxNoClass = new ArrayList<String>();
    ArrayList<String> tutoriaxNomxC = new ArrayList<String>();
    ArrayList<String> tutoriaxPrexC = new ArrayList<String>();
    ArrayList<String> tutoriaxFechxC = new ArrayList<String>();
    ArrayList<String> tutoriaxNoClasexC = new ArrayList<String>();
    ArrayList<String> listaTextxTutoClase = new ArrayList<String>();
    private String usuario, idTutor, idDis;

    public String getIdDis() {
        return idDis;
    }

    public void setIdDis(String idDis) {
        this.idDis = idDis;
    }

    public String getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public MisTutorias() {
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
        return inflater.inflate(R.layout.fragment_mis_tutorias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i = 1; i < 24; i++){
            hora.add(String.valueOf(i));
        }
        dia.put("Lunes", "L");
        dia.put("Martes", "M");
        dia.put("Miercoles","W");
        dia.put("Jueves","J");
        dia.put("Viernes", "V");
        dia.put("Sabado", "S");
        dia.put("Domingo", "D");

        diaContrario.put("L","Lunes");
        diaContrario.put("M", "Martes");
        diaContrario.put("W", "Miercoles");
        diaContrario.put("J","Jueves");
        diaContrario.put("V", "Viernes");
        diaContrario.put("S", "Sabado");
        diaContrario.put("D", "Domingo");

        diaNcompleto.clear();
        for(String i : dia.keySet()){
            diaNcompleto.add(i);
        }

        ListaDia = view.findViewById(R.id.spinnerDia);
        ListaHora = view.findViewById(R.id.spinnerHora);
        listaMiDisponibilidad = view.findViewById(R.id.Lista_Mi_Disponibilidad);
        lxMxCalses = view.findViewById(R.id.Lista_Mis_ClasesView);
        AlertDialog.Builder alertDialogo = new AlertDialog.Builder(this.getContext());
        AlertDialog.Builder alertDialogo1 = new AlertDialog.Builder(this.getContext());
        View row = getLayoutInflater().inflate(R.layout.mitutoriaxclase_popup, null);
        View rows = getLayoutInflater().inflate(R.layout.detalles_tutoria_popup, null);
        tutoriaClase = (ListView) row.findViewById(R.id.listaTutoriaPorClase);
        cliente = (TextView) rows.findViewById(R.id.textCliente_DT);
        precio = (TextView) rows.findViewById(R.id.textPrecio_DT);
        fecha = (TextView) rows.findViewById(R.id.textFecha_DT);
        nclase = (TextView) rows.findViewById(R.id.textClase_DT);
        CargaLista(diaNcompleto, ListaDia);
        CargaLista(hora, ListaHora);
        obtDatos();
        obtDatosUsuarios();
        obtDatosDxT();
        obtDatosCxTxClass();
        BuscarId(getUsuario());
        TextoLisView.clear();
        listaTextxTutoClase.clear();
        obtDatostutoriaxNomxC();

        for(int i = 0; i < DxTxId.size(); i++){
            if(DxTxId.get(i).equalsIgnoreCase(getIdTutor())){
                String palabra = diaContrario.get(DxTxdia.get(i));
                int mas = Integer.parseInt(DxTxhora.get(i)) + 1;
                palabra = palabra + " " + DxTxhora.get(i) + " - " + mas;
                TextoLisView.add(palabra);
            }
        }

        TextoMisClases.clear();

        for(int i = 0; i < CxTxid.size(); i++){
            if(CxTxid.get(i).equalsIgnoreCase(getIdTutor())){
                TextoMisClases.add(CxTxNoClass.get(i));
            }
        }



        ArrayAdapter<String> adapterMiClases = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, TextoMisClases);
        lxMxCalses.setAdapter(adapterMiClases);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, TextoLisView);
        listaMiDisponibilidad.setAdapter(adapter);

        Button guardarDisp = view.findViewById(R.id.buttonDiponibilidad);
        guardarDisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SacarIdDispo();
                insertarDisponibilidad("https://webserviceteachu.000webhostapp.com/index.php/insertDxT.php");
            }
        });

        lxMxCalses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i < tutoriaxNomxC.size(); i++){
                    if(TextoMisClases.get(position).equalsIgnoreCase(tutoriaxNoClasexC.get(i))){
                        String texto = " Nombre: " + tutoriaxNomxC.get(i) + " Fecha: " + tutoriaxFechxC.get(i);
                        listaTextxTutoClase.add(texto);
                    }
                }
                if(!listaTextxTutoClase.isEmpty()){
                    ArrayAdapter<String> adapterTutorias = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaTextxTutoClase);
                    tutoriaClase.setAdapter(adapterTutorias);
                    adapterTutorias.notifyDataSetChanged();
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
                for(int i = 0; i < tutoriaxNomxC.size(); i++){
                    for(int j = 0; j < listaTextxTutoClase.size(); j++){
                        if(listaTextxTutoClase.get(j).contains(tutoriaxNomxC.get(i))){
                            cliente.setText(tutoriaxNomxC.get(i));
                            precio.setText(tutoriaxPrexC.get(i));
                            fecha.setText(tutoriaxFechxC.get(i));
                            nclase.setText(tutoriaxNoClasexC.get(i));
                            break;
                        }
                    }
                }
                mDialog.dismiss();
                alertDialogo1.setView(rows);
                mDialogMT = alertDialogo1.create();
                mDialogMT.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialogMT.show();
            }
        });
    }

    public void llenarmDialod(String nombreC){

    }
    public void SacarIdDispo(){
        for(int i = 0; i < diaTable.size(); i++){
            if(diaTable.get(i).equalsIgnoreCase(dia.get(ListaDia.getSelectedItem().toString())) &&
                    horaTable.get(i).equalsIgnoreCase(ListaHora.getSelectedItem().toString())) {
                setIdDis(iDDisp.get(i));
            }
        }

    }
    public void CargaLista(ArrayList<String> datos, Spinner listado) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, datos);
        listado.setAdapter(adapter);
    }

    public void obtDatos() {
        AsyncHttpClient cliente = new AsyncHttpClient();


        String url = "https://webserviceteachu.000webhostapp.com/index.php/disponibilidad.php";

        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);

        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                iDDisp = obtDatosJason(new String(responseBody), "IdDisponibilidad");
                horaTable = obtDatosJason(new String(responseBody), "hora");
                diaTable = obtDatosJason(new String(responseBody), "dia");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void obtDatosUsuarios() {
        final AsyncHttpClient[] cliente = {new AsyncHttpClient()};
        String url = "https://webserviceteachu.000webhostapp.com/index.php/usuarios.php";
        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);
        cliente[0].post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ips = obtDatosJason(new String(responseBody), "Id_Usuario");
                usuarios = obtDatosJason(new String(responseBody), "Nusuario");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void obtDatosDxT() {
        AsyncHttpClient cliente = new AsyncHttpClient();


        String url = "https://webserviceteachu.000webhostapp.com/index.php/tablasDisponibilidad.php";

        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);

        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                DxTxId = obtDatosJason(new String(responseBody), "Tutor_Id_Usuario");
                DxTxhora = obtDatosJason(new String(responseBody), "hora");
                DxTxdia = obtDatosJason(new String(responseBody), "dia");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void obtDatosCxTxClass() {
        AsyncHttpClient cliente = new AsyncHttpClient();
        String url = "https://webserviceteachu.000webhostapp.com/index.php/TutorXClaseMiClase.php";
        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);
        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                CxTxid = obtDatosJason(new String(responseBody), "Tutor_Id_Usuario");
                CxTxNoClass = obtDatosJason(new String(responseBody), "Nclase");
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
    public void obtDatostutoriaxNomxC() {
        AsyncHttpClient cliente = new AsyncHttpClient();
        String url = "https://webserviceteachu.000webhostapp.com/index.php/TutoriasXUsuarios.php";
        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);
        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                tutoriaxNomxC = obtDatosJason(new String(responseBody), "NomEstudiante");
                tutoriaxPrexC = obtDatosJason(new String(responseBody), "Precio");
                tutoriaxFechxC = obtDatosJason(new String(responseBody), "Fecha");
                tutoriaxNoClasexC = obtDatosJason(new String(responseBody), "Nclase");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void BuscarId (String Usuario){
        for (int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).equalsIgnoreCase(Usuario)){
                setIdTutor(ips.get(i));
            }
        }
    }

    private void insertarDisponibilidad(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext().getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext().getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id_usuario",getIdTutor());
                parametros.put("disponibilidad",getIdDis());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }
}