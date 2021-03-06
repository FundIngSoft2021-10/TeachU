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
        //EditText ed_nombre,ed_apellido,ed_correo,ed_nusuario,ed_contrasena;
        ed_nombre = findViewById(R.id.ET_Nombre_Registro);
        ed_apellido = findViewById(R.id.ET_Apellido_Registro);
        ed_correo = findViewById(R.id.ET_correoIns_Registro);
        ed_nusuario = findViewById(R.id.ET_NombreU_Registro);
        ed_contrasena = findViewById(R.id.ED_contrasena__Registro);
        btn_registro = findViewById(R.id.btn_registro);
        re = findViewById(R.id.radio_Estudiante);
        rt = findViewById(R.id.radio_Tutor);



        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ed_nombre.getText().toString().isEmpty() && !ed_apellido.getText().toString().isEmpty() && !ed_correo.getText().toString().isEmpty() && !ed_nusuario.getText().toString().isEmpty() && !ed_contrasena.getText().toString().isEmpty()) {
                    if(re.isChecked() || rt.isChecked()) {
                        if (re.isChecked()) {

                            ejecutarservicio("https://webserviceteachu.000webhostapp.com/index.php/registro.php");
                            Intent int_registroEst = new Intent(Registro.this, Registro_Estudiante.class);
                            int_registroEst.putExtra("NombreU",ed_nusuario.getText().toString());
                            startActivity(int_registroEst);
                        }
                        if (rt.isChecked()) {
                            ejecutarservicio("https://webserviceteachu.000webhostapp.com/index.php/registro.php");
                            Intent int_registroTut = new Intent(Registro.this, Registro_Tutor.class);
                            int_registroTut.putExtra("NombreU",ed_nusuario.getText().toString());
                            startActivity(int_registroTut);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "SELECCIONE UN TIPO DE USUARIO PARA CONTINUAR", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "LLENE TODOS LOS CAMPOS PARA CONTINUAR", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                parametros.put("id_usuario",cod);
                parametros.put("nombre",ed_nombre.getText().toString());
                parametros.put("apellido",ed_apellido.getText().toString());
                parametros.put("correo",ed_correo.getText().toString());
                parametros.put("nUsuario",ed_nusuario.getText().toString());
                parametros.put("contrasena",ed_contrasena.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}