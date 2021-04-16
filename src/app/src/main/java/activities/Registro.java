package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.teachu.R;
public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        RadioButton re,rt;
        Button btn_registro = findViewById(R.id.btn_registro);
        re = findViewById(R.id.radio_Estudiante);
        rt = findViewById(R.id.radio_Tutor);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(re.isChecked()){
                    Intent int_registroEst = new Intent(Registro.this,Registro_Estudiante.class);
                    startActivity(int_registroEst);
                }
            }
        });
    }
}