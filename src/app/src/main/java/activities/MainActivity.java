package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teachu.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tarea = new TimerTask(){
            @Override
            public void run() {
                Intent login = new Intent(MainActivity.this, Login.class );
                startActivity(login);
                finish();
            }
        };

        Timer tiempoo = new Timer();
        tiempoo.schedule(tarea, 2000);

    }
}