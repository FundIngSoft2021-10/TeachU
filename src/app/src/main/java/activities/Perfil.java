package activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teachu.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class Perfil extends Fragment {


    private String usuario;
    EditText nUsuario, aPellido, cOrreo, nOmbre;
    ArrayList<String> usuarios = new ArrayList<String>();
    ArrayList<String> apellidos = new ArrayList<String>();
    ArrayList<String> correos = new ArrayList<String>();
    ArrayList<String> nombres = new ArrayList<String>();
        public Perfil() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        obtDatos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nUsuario = view.findViewById(R.id.Usuario_Perfil);
        aPellido = view.findViewById(R.id.Apellido_Perfil);
        cOrreo = view.findViewById(R.id.Correo_Perfil);
        nOmbre = view.findViewById(R.id.Nombre_Perfil);
        nUsuario.setHint(this.GetUsuario());
        for(int i = 0; i < usuarios.size(); i++){
            if (GetUsuario().equalsIgnoreCase(usuarios.get(i))){
                aPellido.setHint(apellidos.get(i));
                cOrreo.setHint(correos.get(i));
                nOmbre.setHint(nombres.get(i));
                break;
            }
        }
        Button BtEdit_Nombre = view.findViewById(R.id.btNombre_Perfil);
        Button BtEdit_Apellido = view.findViewById(R.id.btEdit_Apellido);
        Button BtEdit_Correo = view.findViewById(R.id.btEdit_Correo);
        Button BtEdit_Usuario = view.findViewById(R.id.btEdit_Usuario);
        Button BtEdit_Buscador = view.findViewById(R.id.Bt_Guardar_Cambios);

        BtEdit_Nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nOmbre.setEnabled(true);
            }
        });

        BtEdit_Apellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aPellido.setEnabled(true);
            }
        });
        BtEdit_Correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cOrreo.setEnabled(true);
            }
        });
        BtEdit_Usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nUsuario.setEnabled(true);
            }
        });
        BtEdit_Buscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public String GetUsuario(){
        return this.usuario;
    }
    public void SetUsuario(String usuario){
        this.usuario = usuario;
    }
    public void obtDatos() {
        final AsyncHttpClient[] cliente = {new AsyncHttpClient()};
        String url = "https://webserviceteachu.000webhostapp.com/index.php/usuarios.php";
        RequestParams parametros = new RequestParams();
        parametros.put("clase", 18);
        cliente[0].post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                apellidos = obtDatosJason(new String(responseBody), "Apellido");
                usuarios = obtDatosJason(new String(responseBody), "Nusuario");
                correos = obtDatosJason(new String(responseBody), "CorreoInst");
                nombres = obtDatosJason(new String(responseBody), "Nombre");
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
