package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.teachu.R;

public class Registro_Tutor extends AppCompatActivity {
    Spinner spiner_tipodoc;
    EditText N_documento, descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__tutor);
        spiner_tipodoc = findViewById(R.id.spinner2_RegistroTutor);
        N_documento = findViewById(R.id.ET_numcedula_RegistroTutor);
        descripcion = findViewById(R.id.ET_descripcion_RegistroTutor);

        String [] tipo_documento = {"CC","TI","CE"};

        ArrayAdapter<String> adaptador   = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipo_documento);
        spiner_tipodoc.setAdapter(adaptador);
    }
}