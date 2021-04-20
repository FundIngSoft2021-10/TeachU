package activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.teachu.R;


public class Buscar extends Fragment {
    Spinner spiner_Filtro;

    public Buscar() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spiner_Filtro = view.findViewById(R.id.Filtro_Busqueda);
        String [] tipo_Busqueda = {"Tutor","Clases"};

        ArrayAdapter<String> adaptador   = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, tipo_Busqueda);
        spiner_Filtro.setAdapter(adaptador);
    }
}