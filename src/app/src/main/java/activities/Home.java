package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.teachu.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import entities.Usuario;

public class Home extends AppCompatActivity {
    Perfil perfil = new Perfil();
    Buscar buscar = new Buscar();
    Configuracion config = new Configuracion();
    String usuario ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usuario = getIntent().getExtras().getString("Usuario");
        perfil.SetUsuario(usuario);
        buscar.setEstu(usuario);
        BottomNavigationView navigation = findViewById(R.id.NavegationBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(perfil);
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