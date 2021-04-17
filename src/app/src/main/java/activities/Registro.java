package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.teachu.R;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    RadioButton re,rt;
    EditText ed_nombre,ed_apellido,ed_correo,ed_nusuario,ed_contrasena;
    Button btn_registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        RadioButton re,rt;
        EditText ed_nombre,ed_apellido,ed_correo,ed_nusuario,ed_contrasena;
        ed_nombre = findViewById(R.id.ET_Nombre);
        ed_apellido = findViewById(R.id.ET_Apellido);
        ed_correo = findViewById(R.id.ET_correoIns);
        ed_nusuario = findViewById(R.id.ET_NombreU);
        ed_contrasena = findViewById(R.id.ED_contrasena);
        btn_registro = findViewById(R.id.btn_registro);
        re = findViewById(R.id.radio_Estudiante);
        rt = findViewById(R.id.radio_Tutor);



        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(re.isChecked()){
                    ejecutarservicio("http://192.168.100.137:80/serviciosBD/insertar_Usuario.php");
                    //Intent int_registroEst = new Intent(Registro.this,Registro_Estudiante.class);
                    //startActivity(int_registroEst);
                }
                if(rt.isChecked()){
                    Intent int_registroTut = new Intent(Registro.this,Registro_Tutor.class);
                    startActivity(int_registroTut);
                }
            }
        });
    }

    private void ejecutarservicio(String URL){
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
                String cod = "2";
                parametros.put("Id_usuario",cod);
                parametros.put("Nombre","juan");
                parametros.put("Apellido","rincon");
                parametros.put("CorreoInst","correo@correo");
                parametros.put("Nusuario","eljajas");
                parametros.put("contraseña","123325");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}