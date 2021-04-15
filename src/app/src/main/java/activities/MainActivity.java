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
        try{
            ControladorDB db = new ControladorDB();
            Thread t1 = new Thread(db);
            t1.start();
        }
        catch (SQLException | ClassNotFoundException e){
            throw new Error("Error", e);
        }
    }



}