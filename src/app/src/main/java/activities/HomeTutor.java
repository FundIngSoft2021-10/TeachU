package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.teachu.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.MenuItem;

public class HomeTutor extends AppCompatActivity {
    Perfil perfil = new Perfil();
    MisTutorias miTuto = new MisTutorias();
    AgregarTutoria agTutoria = new AgregarTutoria();
    String usuario ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tutor);
        usuario = getIntent().getExtras().getString("Usuario");
        perfil.SetUsuario(usuario);
        agTutoria.setTutor(usuario);
        BottomNavigationView navigation = findViewById(R.id.NavegationBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(perfil);
        loadFragment(miTuto);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.Perfil:
                    loadFragment(perfil);
                    return true;
                case R.id.MisTutorias:
                    loadFragment(miTuto);
                    return true;
                case R.id.Agregar:
                    loadFragment(agTutoria);
                    return true;
            }
            return false;
        }
    };
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Home_Container_Tutor, fragment);
        transaction.commit();
    }
}