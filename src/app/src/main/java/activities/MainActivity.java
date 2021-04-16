package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.teachu.R;

import java.sql.Connection;
import java.sql.SQLException;

import controllers.*;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControladorDB db = new ControladorDB();


    }



}