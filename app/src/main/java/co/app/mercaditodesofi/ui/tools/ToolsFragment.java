package co.app.mercaditodesofi.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import co.app.mercaditodesofi.R;
import co.app.mercaditodesofi.controller.CityController;
import co.app.mercaditodesofi.controller.DepartmentController;
import co.app.mercaditodesofi.model.City;

public class ToolsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        this.root = root;

        ArrayList<String> departments = DepartmentController.getDepartments();

        Spinner dep_spin = root.findViewById(R.id.spinDepartamento);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dep_spin.setAdapter(adapter);
        dep_spin.setOnItemSelectedListener(this);


        return root;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String department = parent.getItemAtPosition(position).toString();

        ArrayList<City> cities = CityController.getCityFromDepartment(department);

        Spinner mun_spin = root.findViewById(R.id.spinMunicipio);

        ArrayAdapter<City> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mun_spin.setAdapter(adapter);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}