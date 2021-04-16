package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teachu.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnretemp = findViewById(R.id.btn_re);
        btnretemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_registroEST = new Intent(MainActivity.this,Registro_Estudiante.class);
                startActivity(int_registroEST);
            }
        });
    }
}