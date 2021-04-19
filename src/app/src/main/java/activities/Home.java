package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.teachu.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.MenuItem;

public class Home extends AppCompatActivity {
    Perfil perfil = new Perfil();
    Buscar buscar = new Buscar();
    Configuracion config = new Configuracion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.NavegationBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(buscar);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.Perfil:
                    loadFragment(perfil);
                    return true;
                case R.id.Buscar:
                    loadFragment(buscar);
                    return true;
                case R.id.Configuracion:
                    loadFragment(config);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Home_Container, fragment);
        transaction.commit();
    }

}